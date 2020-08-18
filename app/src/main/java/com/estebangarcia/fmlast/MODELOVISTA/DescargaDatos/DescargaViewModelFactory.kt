package com.estebangarcia.fmlast.MODELOVISTA.DescargaDatos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio


class DescargaViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DescargaViewModel(lastFmRepositorio) as T
    }
}