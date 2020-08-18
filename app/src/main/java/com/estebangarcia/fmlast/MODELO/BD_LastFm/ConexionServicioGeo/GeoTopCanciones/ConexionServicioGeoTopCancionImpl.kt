package com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopCanciones

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.estebangarcia.fmlast.INTERNO.NoConexionExcepcion
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.ApiDescargaTopGeoCancion
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.RespuestaGeoTopCanciones
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.*

class ConexionServicioGeoTopCancionImpl (
    private val apiDescargaGeoTopCancion: ApiDescargaTopGeoCancion
):ConexionServicioGeoTopCancion{

    private val _descargaGeoTopCancion= MutableLiveData<RespuestaGeoTopCanciones>()
    override val descargaGeoTopCancion: LiveData<RespuestaGeoTopCanciones>
        get() = _descargaGeoTopCancion

    override suspend fun traerGeoTopCanciones( apiKey: String) {
        var rt:RespuestaGeoTopCanciones
        try {
            val respuestaDescarga=apiDescargaGeoTopCancion
                .DescargaGeoTopCanciones(apiKey).await()
            rt=respuestaDescarga
            Log.i("Respuesta","Descarga servicio geo:"+respuestaDescarga)
        } catch (e: Exception){
            Log.e("Error"," Conexion servicio geo:"+e)
            var listaimagenes= listOf(Image("",""),Image("",""),Image("",""),Image("",""))
            var artista=Artist("","","")
            var rank=AttrRank("1")
            var streamable=Streamable("","")
            var canciones=listOf(Track(artista,rank,"",listaimagenes,"0","","Error",streamable,""))
            var attr= Attr("","","","","")
            var geotopcanciones=GeoTopCanciones(attr,canciones)
            val r=RespuestaGeoTopCanciones(geotopcanciones)
            rt=r
        }
        catch (errorConexion: NoConexionExcepcion){
            Log.e("Error"," Conexion servicio geo:"+errorConexion)
            var listaimagenes= listOf(Image("",""),Image("",""),Image("",""),Image("",""))
            var artista=Artist("","","")
            var rank=AttrRank("1")
            var streamable=Streamable("","")
            var canciones=listOf(Track(artista,rank,"",listaimagenes,"0","","NoInternet",streamable,""))
            var attr= Attr("","","","","")
            var geotopcanciones=GeoTopCanciones(attr,canciones)
            val r=RespuestaGeoTopCanciones(geotopcanciones)
            rt=r
        }

        _descargaGeoTopCancion.postValue(rt)
    }
}