package com.estebangarcia.fmlast.MODELO.Repositorio

import androidx.lifecycle.LiveData
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.Artista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion

interface LastFmRepositorio {
     suspend fun limpiarroom()
     suspend fun obtenerTopArtistas():LiveData<out List<EntidadArtista>>
     suspend fun ConsultaServicioGeoTopArtistas()
     suspend fun obtenerTopCanciones():LiveData<out List<EntidadCancion>>
     suspend fun ConsultaServicioGeoTopCanciones()
     suspend fun ConsultaServicioGeo()
}