package com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopArtista

import androidx.lifecycle.LiveData
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.RespuestaGeoTopArtistas

interface ConexionServicioGeoTopArtista {

    val descargaGeoTopArtista:LiveData<RespuestaGeoTopArtistas>
    suspend fun traerGeoTopArtistas(apiKey:String)

}