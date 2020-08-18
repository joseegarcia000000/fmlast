package com.estebangarcia.fmlast.MODELOVISTA.ArtistasFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.estebangarcia.fmlast.INTERNO.lazyDeferred
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.Artista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import kotlinx.coroutines.Deferred

class ArtistaViewModel(
private val lastFmRepositorio: LastFmRepositorio
): ViewModel() {
    fun obtenertopartistas(): Deferred<LiveData<out List<EntidadArtista>>> {
        val topartistas by lazyDeferred {
            lastFmRepositorio.obtenerTopArtistas()
        }
        return topartistas
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    fun consultarserviciogeotopartistas(): Deferred<Unit> {
        val descarga by lazyDeferred {
            lastFmRepositorio.ConsultaServicioGeoTopArtistas()
        }
        return descarga
    }
}