package com.estebangarcia.fmlast.MODELO.BD_persistencia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao.GeoTopArtistasDao
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao.GeoTopCancionesDao
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.Artista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion

@Database(
    entities = [EntidadArtista::class,EntidadCancion::class],
    version = 1
)
abstract class lastfmBDPersistencia:RoomDatabase(){

    abstract  fun geoTopCancionesDao():GeoTopCancionesDao
    abstract fun geoTopArtistasDao():GeoTopArtistasDao


    companion object{// se hace un objeto complementario para instanciar en un solo lugar la base de datos
    @Volatile private var instancia:lastfmBDPersistencia?=null

        private val LOCK=Any()
        operator fun invoke(context: Context)= instancia?: synchronized(LOCK){
            instancia?: construirbasededatos(context).also{ instancia=it}
        }

        private fun construirbasededatos(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            lastfmBDPersistencia::class.java,
            "lastfmbdpersistencia"
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}