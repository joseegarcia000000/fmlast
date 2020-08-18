package com.estebangarcia.fmlast.MODELOVISTA.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio

class MainViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(lastFmRepositorio) as T
    }
}