package com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas

import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.google.gson.annotations.SerializedName


data class RespuestaGeoTopArtistas(
    @SerializedName("topartists")
    val geotopartista: GeoTopArtista
)