package com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopCanciones

import androidx.lifecycle.LiveData
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.RespuestaGeoTopCanciones

interface ConexionServicioGeoTopCancion {

    val descargaGeoTopCancion: LiveData<RespuestaGeoTopCanciones>
    suspend fun traerGeoTopCanciones(apiKey:String)
}