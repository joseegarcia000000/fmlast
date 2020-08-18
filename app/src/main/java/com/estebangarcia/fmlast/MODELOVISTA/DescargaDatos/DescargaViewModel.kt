package com.estebangarcia.fmlast.MODELOVISTA.DescargaDatos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.estebangarcia.fmlast.INTERNO.lazyDeferred
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import kotlinx.coroutines.Deferred

class DescargaViewModel(
    private val lastFmRepositorio: LastFmRepositorio
):ViewModel() {

    fun consultarserviciogeo():Deferred<Unit>{
        val descarga by lazyDeferred {
            lastFmRepositorio.ConsultaServicioGeo()
        }
        return descarga
    }
    fun obtenertopcanciones(): Deferred<LiveData<out List<EntidadCancion>>> {
        val topcanciones by lazyDeferred {
            lastFmRepositorio.obtenerTopCanciones()
        }
        return topcanciones
    }
    fun obtenertopartistas(): Deferred<LiveData<out List<EntidadArtista>>> {
        val topartistas by lazyDeferred {
            lastFmRepositorio.obtenerTopArtistas()
        }
        return topartistas
    }
}