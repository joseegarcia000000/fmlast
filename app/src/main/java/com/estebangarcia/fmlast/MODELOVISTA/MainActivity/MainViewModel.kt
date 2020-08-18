package com.estebangarcia.fmlast.MODELOVISTA.MainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.estebangarcia.fmlast.INTERNO.lazyDeferred
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.Artista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import kotlinx.coroutines.Deferred

class MainViewModel(
    private val lastFmRepositorio: LastFmRepositorio):ViewModel() {

    fun consultartopartistas():Deferred<LiveData<out List<EntidadArtista>>>{
        val topartistas by lazyDeferred {
            lastFmRepositorio.obtenerTopArtistas()
        }
        return topartistas
    }
}