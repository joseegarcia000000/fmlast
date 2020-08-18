package com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.estebangarcia.fmlast.INTERNO.lazyDeferred
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import kotlinx.coroutines.Deferred

class CancionesViewModel(
    private val lastFmRepositorio: LastFmRepositorio
):ViewModel() {
    fun obtenertopcanciones(): Deferred<LiveData<out List<EntidadCancion>>> {
        val topcanciones by lazyDeferred {
            lastFmRepositorio.obtenerTopCanciones()
        }
        return topcanciones
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    fun consultarserviciogeotopcanciones(): Deferred<Unit> {
        val descarga by lazyDeferred {
            lastFmRepositorio.ConsultaServicioGeoTopCanciones()
        }
        return descarga
    }
}