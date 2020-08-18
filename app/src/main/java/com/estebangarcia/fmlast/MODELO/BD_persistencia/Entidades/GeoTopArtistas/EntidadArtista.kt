package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topartistassqlite" )
data class EntidadArtista (
    val image: String,
    val listeners: String,
    @PrimaryKey(autoGenerate = false)
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)
