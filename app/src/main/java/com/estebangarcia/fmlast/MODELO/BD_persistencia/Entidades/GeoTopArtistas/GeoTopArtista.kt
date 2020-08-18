package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GeoTopArtista(
    @SerializedName("artist")
    val artista: List<Artista>,
    @SerializedName("@attr")
    val attr: Attr

)