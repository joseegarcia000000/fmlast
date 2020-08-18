package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas


import com.google.gson.annotations.SerializedName

data class Image(
    val size: String,
    @SerializedName("#text")
    val text: String
)