package com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion

@Dao
interface GeoTopCancionesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarActualizarTopCanciones(TopCanciones:List<EntidadCancion>)
    @Query("DELETE FROM topcancionessqlite")
    fun limpiarTopCanciones()
    @Query("SELECT * from topcancionessqlite")
    fun obtenerTopCanciones(): LiveData<List<EntidadCancion>>
}