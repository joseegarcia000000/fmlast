package com.estebangarcia.fmlast.VISTA.Actividades

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELOVISTA.MainActivity.MainViewModel
import com.estebangarcia.fmlast.MODELOVISTA.MainActivity.MainViewModelFactory
import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.VISTA.Base.alcanceActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.lang.Exception
import java.lang.Thread.sleep

class MainActivity : alcanceActivity() ,KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory:MainViewModelFactory by instance()
    private  lateinit var viewModel:MainViewModel
    private lateinit var interaccion:Intent
    private var Estado=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ocultarBotonesVirtuales()
        }
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(!Estado){
            interaccion= Intent(baseContext, IniciarSesion::class.java)
            launch {
                withContext(Dispatchers.Main){
                    Estado=true
                    val topartistas=viewModel.consultartopartistas().await()
                    topartistas.observe(this@MainActivity, Observer{

                        if(it==null||it.size<=1){
                            interaccion= Intent(baseContext, IniciarSesion::class.java)
                        }else{
                            interaccion= Intent(baseContext, SesionLastFm::class.java)
                        }
                    })
                    delay(5000)
                }.run { lanzarActividad() }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("ESTADO",Estado)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Estado=savedInstanceState.getBoolean("ESTADO")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun lanzarActividad(){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

                ActivityOptionsCompat.makeCustomAnimation(this@MainActivity,R.anim.fade_in,R.anim.fade_out).toBundle()
                startActivity(interaccion)
                ActivityCompat.finishAfterTransition(this@MainActivity)
            }else{
                finish()
            }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun ocultarBotonesVirtuales() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
