package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones


import com.google.gson.annotations.SerializedName

data class Track(
    val artist: Artist,
    @SerializedName("@attr")
    val attr: AttrRank,
    val duration: String,
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)