package com.estebangarcia.fmlast.MODELO.Repositorio

import androidx.lifecycle.LiveData
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopArtista.ConexionServicioGeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopCanciones.ConexionServicioGeoTopCancion
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.RespuestaGeoTopArtistas
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.RespuestaGeoTopCanciones
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao.GeoTopArtistasDao
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Dao.GeoTopCancionesDao
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.Artista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.GeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion
import com.estebangarcia.fmlast.MODELO.BD_persistencia.lastfmBDPersistencia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LastFmRepositorioImpl(
    private val baseDeDatos:lastfmBDPersistencia,
    private val geoTopArtistasDao: GeoTopArtistasDao,
    private val conexionServicioGeoTopArtista: ConexionServicioGeoTopArtista,
    private val geoTopCancionesDao: GeoTopCancionesDao,
    private val conexionServicioGeoTopCancion: ConexionServicioGeoTopCancion
):LastFmRepositorio {
    init {
        conexionServicioGeoTopArtista.descargaGeoTopArtista.observeForever{
            persistenciaTopArtistas(it)
        }

        conexionServicioGeoTopCancion.descargaGeoTopCancion.observeForever{
            persistenciaTopCancion(it)
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun limpiarroom(){
        baseDeDatos.clearAllTables()
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun obtenerTopArtistas():LiveData<out List<EntidadArtista>>{

        return withContext(Dispatchers.IO){
            return@withContext geoTopArtistasDao.obtenerTopArtistas()
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun persistenciaTopArtistas(respuestaGeoTopArtistas: RespuestaGeoTopArtistas){
        GlobalScope.launch(Dispatchers.IO) {
            var lista=ArrayList<EntidadArtista>()
            if(respuestaGeoTopArtistas.geotopartista.artista.size>1){
                for (index in respuestaGeoTopArtistas.geotopartista.artista.indices){
                    lista.add(
                        EntidadArtista(
                            respuestaGeoTopArtistas.geotopartista.artista[index].image[3].text,
                            respuestaGeoTopArtistas.geotopartista.artista[index].listeners,
                            respuestaGeoTopArtistas.geotopartista.artista[index].mbid,
                            respuestaGeoTopArtistas.geotopartista.artista[index].name,
                            respuestaGeoTopArtistas.geotopartista.artista[index].streamable,
                            respuestaGeoTopArtistas.geotopartista.artista[index].url)
                    )
                }
                geoTopArtistasDao.limppiarTopArtistas()
                geoTopArtistasDao.insertarActualizarTopArtistas(lista)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun ConsultaServicioGeoTopArtistas(){
        conexionServicioGeoTopArtista.traerGeoTopArtistas("829751643419a7128b7ada50de590067")
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun obtenerTopCanciones():LiveData<out List<EntidadCancion>>{

        return withContext(Dispatchers.IO){
            return@withContext geoTopCancionesDao.obtenerTopCanciones()
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun persistenciaTopCancion(respuestaGeoTopCanciones: RespuestaGeoTopCanciones){
        GlobalScope.launch(Dispatchers.IO) {
            var lista=ArrayList<EntidadCancion>()
            if(respuestaGeoTopCanciones.geotopcanciones.track.size>1){
                for (index in respuestaGeoTopCanciones.geotopcanciones.track.indices){
                    lista.add(
                        EntidadCancion(
                            respuestaGeoTopCanciones.geotopcanciones.track[index].artist.name,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].artist.mbid,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].artist.url,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].name,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].mbid,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].attr.rank,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].duration,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].image[3].text,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].listeners,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].streamable.text,
                            respuestaGeoTopCanciones.geotopcanciones.track[index].url
                            )
                    )
                }
                geoTopCancionesDao.limpiarTopCanciones()
                geoTopCancionesDao.insertarActualizarTopCanciones(lista)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun ConsultaServicioGeoTopCanciones(){
        conexionServicioGeoTopCancion.traerGeoTopCanciones("829751643419a7128b7ada50de590067")
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override suspend fun ConsultaServicioGeo(){
        conexionServicioGeoTopCancion.traerGeoTopCanciones("829751643419a7128b7ada50de590067")
        conexionServicioGeoTopArtista.traerGeoTopArtistas("829751643419a7128b7ada50de590067")
    }
}