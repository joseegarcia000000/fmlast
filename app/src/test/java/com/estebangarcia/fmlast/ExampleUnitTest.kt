package com.estebangarcia.fmlast

import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.ApiDescargaTopGeoArtista
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.ApiDescargaTopGeoCancion
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    /*
    Este test prueba el servicio web GEO topartistas y verifica que el JSON que obtiene con retrofit es igual al que se obtiene
     con post man, para esta prueba se configura el servicio sin el interceptor de conectividad (Esto se debe a que el interceptor
     solo funciona en un dispositivo con flags de conectividad y en ambientes de text no es posible evaluar estos flags)
     y se configura la consulta con un limite de 1 y pais colombia, pagina 1 se deja en comentario ya que el ApiDescarga
      para desacticar el interceptor, tambien se quita la intancia en la inyeecion de dependencias de la aplicacion
*/
/*
    @Test
    fun consultaTopArtistas(){
        val apiDescargaTopGeoArtista =
            ApiDescargaTopGeoArtista()
        var textorespuesta=""

            val respuestaConsulta= runBlocking {
             apiDescargaTopGeoArtista.DescargaGeoTopArtistas(
            "829751643419a7128b7ada50de590067")
                 .await()
            }
            textorespuesta=respuestaConsulta.geotopartista.toString()

        val textoesperado="GeoTopArtista(artista=[Artista(image=[Image(size=small, text=https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=medium, text=https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=large, text=https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=extralarge, text=https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=mega, text=https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png)], listeners=4245350, mbid=420ca290-76c5-41af-999e-564d7c71f1a7, name=Queen, streamable=0, url=https://www.last.fm/music/Queen)], attr=Attr(country=Colombia, page=1, perPage=1, total=397846, totalPages=397846))"
        assertEquals(textoesperado, textorespuesta)
    }
    /*
     Este test prueba el servicio web GEO topcancion y verifica que el JSON que obtiene con retrofit es igual al que se obtiene
     con post man, para esta prueba se configura el servicio sin el interceptor de conectividad (Esto se debe a que el interceptor
     solo funciona en un dispositivo con flags de conectividad y en ambientes de text no es posible evaluar estos flags)
     y se configura la consulta con un limite de 1 y pais colombia, pagina 1 se deja en comentario ya que el ApiDescarga
      para desacticar el interceptor, tambien se quita la intancia en la inyeecion de dependencias de la aplicacion
 */

    @Test
    fun consultaTopCanciones(){
        val apiDescargaTopGeoCancion =
            ApiDescargaTopGeoCancion()
        var textorespuesta=""

        val respuestaConsulta= runBlocking {
            apiDescargaTopGeoCancion.DescargaGeoTopCanciones(
                "829751643419a7128b7ada50de590067")
                .await()
        }
        textorespuesta=respuestaConsulta.geotopcanciones.toString()

        val textoesperado="GeoTopCanciones(attr=Attr(country=Colombia, page=1, perPage=1, total=3174576, totalPages=3174576), track=[Track(artist=Artist(mbid=ada7a83c-e3e1-40f1-93f9-3e73dbc9298a, name=Arctic Monkeys, url=https://www.last.fm/music/Arctic+Monkeys), attr=AttrRank(rank=0), duration=272, image=[Image(size=small, text=https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=medium, text=https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=large, text=https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png), Image(size=extralarge, text=https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png)], listeners=1029536, mbid=f1e57531-e0df-4b3e-938f-1ae30c5b1a11, name=Do I Wanna Know?, streamable=Streamable(fulltrack=0, text=0), url=https://www.last.fm/music/Arctic+Monkeys/_/Do+I+Wanna+Know%3F)])"
        assertEquals(textoesperado, textorespuesta)
    }
*/

}
