package com.estebangarcia.fmlast.MODELOVISTA.ArtistasFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment.CancionesViewModel

class ArtistaViewModelFactory (
    private val lastFmRepositorio: LastFmRepositorio
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistaViewModel(lastFmRepositorio) as T
    }
}