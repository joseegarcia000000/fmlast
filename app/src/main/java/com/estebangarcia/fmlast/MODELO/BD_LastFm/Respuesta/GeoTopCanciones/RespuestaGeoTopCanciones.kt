package com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones


import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.GeoTopCanciones
import com.google.gson.annotations.SerializedName

data class RespuestaGeoTopCanciones(
    @SerializedName("tracks")
    val geotopcanciones: GeoTopCanciones
)