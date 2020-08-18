package com.estebangarcia.fmlast.MODELOVISTA.SesionLastFm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.estebangarcia.fmlast.INTERNO.lazyDeferred
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import kotlinx.coroutines.Deferred

class SesionViewModel (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModel() {

fun cerrarsesion():Deferred<Unit>{
    val limpiarroom by lazyDeferred {
    lastFmRepositorio.limpiarroom()
    }
    return limpiarroom
}


}