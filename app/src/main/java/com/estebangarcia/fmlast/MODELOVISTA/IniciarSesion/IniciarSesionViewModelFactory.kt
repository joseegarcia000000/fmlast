package com.estebangarcia.fmlast.MODELOVISTA.IniciarSesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import com.estebangarcia.fmlast.MODELOVISTA.MainActivity.MainViewModel

class IniciarSesionViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IniciarSesionViewModel(lastFmRepositorio) as T
    }
}