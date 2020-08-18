package com.estebangarcia.fmlast.MODELOVISTA.SesionLastFm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio

class SesionViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SesionViewModel(lastFmRepositorio) as T
    }
}