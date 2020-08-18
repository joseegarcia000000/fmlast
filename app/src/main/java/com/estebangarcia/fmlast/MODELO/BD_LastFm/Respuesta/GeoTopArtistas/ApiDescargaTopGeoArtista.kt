package com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas

import com.estebangarcia.fmlast.INTERNO.Constantes
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Interceptor.InterceptorConectividad
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiDescargaTopGeoArtista {
//100
    @POST("/2.0/?method=geo.gettopartists&country=colombia&limit=100&page=1&format=json")
    @FormUrlEncoded
    fun DescargaGeoTopArtistas(
        @Field("api_key")api_key:String
    ): Deferred<RespuestaGeoTopArtistas>
    companion object{
        operator fun invoke(
            interceptorConectividad: InterceptorConectividad
        ): ApiDescargaTopGeoArtista {

            val respuestaInterceptor=Interceptor{
                val url=it.request()
                    .url()
                    .newBuilder()
                    .build()
                val respuesta=it.request()
                    .newBuilder()
                    .url(url)
                    .build()
                val res=it.proceed(respuesta)
                return@Interceptor res
            }


            val confirmacionHttpClient=OkHttpClient.Builder()
                .addInterceptor(respuestaInterceptor)
                .addInterceptor(interceptorConectividad)
                .build()

            return Retrofit.Builder()
                .client(confirmacionHttpClient)
                .baseUrl("http://ws.audioscrobbler.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiDescargaTopGeoArtista::class.java)
        }
    }
}