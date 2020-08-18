package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas

data class Artista(
    val image: List<Image>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)