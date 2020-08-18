package com.estebangarcia.fmlast.VISTA.Actividades

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.estebangarcia.fmlast.MODELOVISTA.IniciarSesion.IniciarSesionViewModel
import com.estebangarcia.fmlast.MODELOVISTA.IniciarSesion.IniciarSesionViewModelFactory
import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.VISTA.Base.alcanceActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class IniciarSesion : alcanceActivity(),KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: IniciarSesionViewModelFactory by instance()
    private  lateinit var viewModel: IniciarSesionViewModel
    private lateinit var interaccion:Intent
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)
        mAuth = FirebaseAuth.getInstance()
        viewModel= ViewModelProvider(this,viewModelFactory).get(IniciarSesionViewModel::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ocultarBotonesVirtuales()
        }
        cardCarga.visibility=View.GONE
        //-----------------------------------------------------------
        botonSalir.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        botonIniciarSesion.setOnClickListener {
            cardCarga.visibility=View.VISIBLE
            textocarga.text="Iniciando Sesión"

            launch {
                if (!campoClave.text.toString().trim().isEmpty() && !campoCorreo.text.toString().trim().isEmpty()) {
                    withContext(Dispatchers.Main) {
                        mAuth?.signInWithEmailAndPassword(campoCorreo.text.toString().trim(),campoClave.text.toString().trim())
                            ?.addOnCompleteListener {
                                if (it.isSuccessful()) {
                                    interaccion= Intent(baseContext, DescargaDatos::class.java)
                                    ActivityOptionsCompat.makeCustomAnimation(this@IniciarSesion,R.anim.fade_in,R.anim.fade_out).toBundle()
                                    startActivity(interaccion)
                                    ActivityCompat.finishAfterTransition(this@IniciarSesion)
                                } else{
                                    textocarga.text="Crecendiales Incorrectas o no tienes internet"
                                }

                                }
                         }
                } else {
                    textocarga.text="Tienes campos vacios"
                }
                delay(1500L)
                cardCarga.visibility=View.GONE
            }

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun ocultarBotonesVirtuales() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
