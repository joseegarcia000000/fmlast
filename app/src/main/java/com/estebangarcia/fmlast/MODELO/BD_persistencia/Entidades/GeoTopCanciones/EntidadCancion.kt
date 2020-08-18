package com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topcancionessqlite" )
data class EntidadCancion (
    val nombreartista: String,
    val mbidartista: String,
    val urlartista: String,
    val nombrecancion: String,
    @PrimaryKey(autoGenerate = false)
    val mbidcancion: String,
    val rank: String,
    val duration: String,
    val image: String,
    val listeners: String,
    val streamable: String,
    val urlCancion: String
)