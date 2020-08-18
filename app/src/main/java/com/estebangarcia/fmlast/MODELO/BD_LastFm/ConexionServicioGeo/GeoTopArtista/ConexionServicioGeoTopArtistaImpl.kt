package com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopArtista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.estebangarcia.fmlast.INTERNO.NoConexionExcepcion
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.ApiDescargaTopGeoArtista
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.RespuestaGeoTopArtistas
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.*

class ConexionServicioGeoTopArtistaImpl(
    private val apiDescargaGeoTopArtista: ApiDescargaTopGeoArtista
):ConexionServicioGeoTopArtista{

    private val _descargaGeoTopArtistas=MutableLiveData<RespuestaGeoTopArtistas>()
    override val descargaGeoTopArtista:LiveData<RespuestaGeoTopArtistas>
    get() = _descargaGeoTopArtistas

    override suspend fun traerGeoTopArtistas( apiKey: String) {
       var rt:RespuestaGeoTopArtistas
        try {
            val respuestaDescarga=apiDescargaGeoTopArtista
                .DescargaGeoTopArtistas(apiKey).await()
            rt=respuestaDescarga
        } catch (e: Exception){
            var listaimagenes= listOf(Image("",""),Image("",""),Image("",""),Image("",""))
            var artista=listOf(Artista(listaimagenes,"0","Servidor","Error","0",""))
            var attr=Attr("","","","","")
            var geotopartista=GeoTopArtista(artista,attr)
            val r=RespuestaGeoTopArtistas(geotopartista)
            rt=r
        }
        catch (errorConexion:NoConexionExcepcion){
            var listaimagenes= listOf(Image("",""),Image("",""),Image("",""),Image("",""))
            var artista=listOf(Artista(listaimagenes,"0","","NoInternet","0",""))
            var attr=Attr("","","","","")
            var geotopartista=GeoTopArtista(artista,attr)
            val r=RespuestaGeoTopArtistas(geotopartista)
            rt=r
        }

        _descargaGeoTopArtistas.postValue(rt)
    }
}