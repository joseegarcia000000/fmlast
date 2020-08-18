package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones


import com.google.gson.annotations.SerializedName

data class Streamable(
    val fulltrack: String,
    @SerializedName("#text")
    val text: String
)