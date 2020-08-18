package com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista

@Dao
interface GeoTopArtistasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarActualizarTopArtistas(TopArtistas:List<EntidadArtista>)
    @Query("DELETE FROM topartistassqlite")
    fun limppiarTopArtistas()
    @Query("SELECT * from topartistassqlite")
    fun obtenerTopArtistas(): LiveData<List<EntidadArtista>>
}