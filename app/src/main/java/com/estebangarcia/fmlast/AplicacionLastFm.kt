package com.estebangarcia.fmlast

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopArtista.ConexionServicioGeoTopArtista
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopArtista.ConexionServicioGeoTopArtistaImpl
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopCanciones.ConexionServicioGeoTopCancion
import com.estebangarcia.fmlast.MODELO.BD_LastFm.ConexionServicioGeo.GeoTopCanciones.ConexionServicioGeoTopCancionImpl
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Interceptor.InterceptorConectividad
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Interceptor.InterceptorConectividadImpl
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopArtistas.ApiDescargaTopGeoArtista
import com.estebangarcia.fmlast.MODELO.BD_LastFm.Respuesta.GeoTopCanciones.ApiDescargaTopGeoCancion
import com.estebangarcia.fmlast.MODELO.BD_persistencia.lastfmBDPersistencia
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorio
import com.estebangarcia.fmlast.MODELO.Repositorio.LastFmRepositorioImpl
import com.estebangarcia.fmlast.MODELOVISTA.ArtistasFragment.ArtistaViewModelFactory
import com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment.CancionesViewModelFactory
import com.estebangarcia.fmlast.MODELOVISTA.DescargaDatos.DescargaViewModelFactory
import com.estebangarcia.fmlast.MODELOVISTA.IniciarSesion.IniciarSesionViewModelFactory
import com.estebangarcia.fmlast.MODELOVISTA.MainActivity.MainViewModelFactory
import com.estebangarcia.fmlast.MODELOVISTA.SesionLastFm.SesionViewModelFactory
import com.estebangarcia.fmlast.VISTA.Actividades.DescargaDatos
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AplicacionLastFm:Application(),KodeinAware {

    override  val kodein= Kodein.lazy {
        import(androidXModule(this@AplicacionLastFm))
        bind() from singleton { lastfmBDPersistencia(instance()) }
        bind() from singleton { instance<lastfmBDPersistencia>() .geoTopArtistasDao()}
        bind() from singleton { instance<lastfmBDPersistencia>() .geoTopCancionesDao()}
        bind<InterceptorConectividad>() with singleton { InterceptorConectividadImpl(instance()) }
        bind() from singleton { ApiDescargaTopGeoArtista(instance()) }
        bind() from singleton { ApiDescargaTopGeoCancion(instance()) }
        bind<ConexionServicioGeoTopArtista>() with singleton { ConexionServicioGeoTopArtistaImpl(instance()) }
        bind<ConexionServicioGeoTopCancion>() with singleton { ConexionServicioGeoTopCancionImpl(instance()) }
        bind<LastFmRepositorio>() with singleton { LastFmRepositorioImpl(instance(),instance(),instance(),instance(),instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { IniciarSesionViewModelFactory(instance()) }
        bind() from provider { DescargaViewModelFactory(instance()) }
        bind() from provider { CancionesViewModelFactory(instance()) }
        bind() from provider { ArtistaViewModelFactory(instance()) }
        bind() from provider { SesionViewModelFactory(instance()) }
    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}