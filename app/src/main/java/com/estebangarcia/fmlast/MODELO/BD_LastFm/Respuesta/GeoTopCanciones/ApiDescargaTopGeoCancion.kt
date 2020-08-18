package com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones

import com.estebangarcia.fmlast.MODELO.BD_LastFm.Interceptor.InterceptorConectividad
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiDescargaTopGeoCancion {

    @POST("/2.0/?method=geo.gettoptracks&country=colombia&limit=500&page=1&format=json")
    @FormUrlEncoded
    fun DescargaGeoTopCanciones(
        @Field("api_key")api_key:String
    ): Deferred<RespuestaGeoTopCanciones>

    companion object{
        operator fun invoke(
            interceptorConectividad: InterceptorConectividad
        ): ApiDescargaTopGeoCancion {

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

            val confirmacionHttpClient= OkHttpClient.Builder()
                .addInterceptor(respuestaInterceptor)
                //.addInterceptor(interceptorConectividad)
                .build()

            return Retrofit.Builder()
                .client(confirmacionHttpClient)
                .baseUrl("http://ws.audioscrobbler.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiDescargaTopGeoCancion::class.java)
        }
    }
}
