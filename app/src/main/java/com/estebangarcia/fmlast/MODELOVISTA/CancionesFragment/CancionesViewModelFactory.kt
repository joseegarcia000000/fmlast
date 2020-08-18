package com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio

class CancionesViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CancionesViewModel(lastFmRepositorio) as T
    }
}