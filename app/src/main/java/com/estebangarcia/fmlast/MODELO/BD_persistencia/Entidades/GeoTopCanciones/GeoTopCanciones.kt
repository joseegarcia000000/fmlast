package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones


import com.google.gson.annotations.SerializedName

data class GeoTopCanciones(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)