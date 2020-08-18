package com.estebangarcia.fmlast.MODELO.BD_LastFm.Interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.estebangarcia.fmlast.INTERNO.NoConexionExcepcion
import okhttp3.Interceptor
import okhttp3.Response

@Suppress("DEPRECATION")
class InterceptorConectividadImpl(
    contexto: Context
) : InterceptorConectividad {

    private val appContexto=contexto.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!estaConectado())
            throw NoConexionExcepcion()
        return chain.proceed(chain.request())
    }

    private fun estaConectado():Boolean{
        var resultado = false
        val administradorConectividad=appContexto.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = administradorConectividad.activeNetwork ?: return false
            val actNw =
                administradorConectividad.getNetworkCapabilities(networkCapabilities) ?: return false
            resultado = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            administradorConectividad.run {
                administradorConectividad.activeNetworkInfo?.run {
                    resultado = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return resultado
    }
}