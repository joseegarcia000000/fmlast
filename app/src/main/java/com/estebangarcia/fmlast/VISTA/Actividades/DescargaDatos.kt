package com.estebangarcia.fmlast.VISTA.Actividades

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELOVISTA.DescargaDatos.DescargaViewModel
import com.estebangarcia.fmlast.MODELOVISTA.DescargaDatos.DescargaViewModelFactory
import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.VISTA.Base.alcanceActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class DescargaDatos : alcanceActivity() ,KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory: DescargaViewModelFactory by instance()
    private  lateinit var viewModel: DescargaViewModel
    private lateinit var interaccion:Intent
    private var Estadodescarga=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descarga_datos)
        viewModel= ViewModelProvider(this,viewModelFactory).get(DescargaViewModel::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ocultarBotonesVirtuales()
        }
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(!Estadodescarga){
            launch {
                withContext(Dispatchers.Main){
                    Estadodescarga=true
                    viewModel.consultarserviciogeo().await()
                }.run {
                        lanzarActividad()
                    }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun lanzarActividad(){
            interaccion= Intent(baseContext, SesionLastFm::class.java)
            ActivityOptionsCompat.makeCustomAnimation(this@DescargaDatos,R.anim.fade_in,R.anim.fade_out).toBundle()
            startActivity(interaccion)
            ActivityCompat.finishAfterTransition(this@DescargaDatos)
    }
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean("ESTADODESCARGA",Estadodescarga)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Estadodescarga=savedInstanceState.getBoolean("ESTADODESCARGA")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun ocultarBotonesVirtuales() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
