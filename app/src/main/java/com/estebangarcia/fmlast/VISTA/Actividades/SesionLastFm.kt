package com.estebangarcia.fmlast.VISTA.Actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.estebangarcia.fmlast.MODELOVISTA.MainActivity.MainViewModel
import com.estebangarcia.fmlast.MODELOVISTA.SesionLastFm.SesionViewModel
import com.estebangarcia.fmlast.MODELOVISTA.SesionLastFm.SesionViewModelFactory
import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.VISTA.Base.alcanceActivity
import kotlinx.android.synthetic.main.activity_sesion_last_fm.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SesionLastFm : alcanceActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: SesionViewModelFactory by instance()
    private  lateinit var viewModel: SesionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion_last_fm)
        viewModel= ViewModelProvider(this,viewModelFactory).get(SesionViewModel::class.java)
        textocontrolhost.visibility=View.GONE
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
            Log.i("restaurar",""+textocontrolhost.text)
        }
        navegador()
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("VA",textocontrolhost.text.toString())
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getString("VA")!=null){
            textocontrolhost.text=savedInstanceState.getString("VA").toString()
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun navegador(){
        navegadormenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.topcanciones -> {
                    if(!textocontrolhost.text.equals("1")){
                        textocontrolhost.text="1"
                        navegacion_host_fragmento.findNavController().navigate(R.id.action_artistasFragment_to_cancionesFragment)
                        return@setOnNavigationItemSelectedListener true
                    }

                }
                R.id.topartistas -> {
                    if(!textocontrolhost.text.equals("2")){
                        textocontrolhost.text="2"
                        navegacion_host_fragmento.findNavController().navigate(R.id.action_cancionesFragment_to_artistasFragment)
                        return@setOnNavigationItemSelectedListener true
                    }

                }
                R.id.salirfragmento -> {
                    cuadroDialogo()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     override fun cuadroDialogo(){
        var constructor=AlertDialog.Builder(this@SesionLastFm)
        constructor.setTitle("Atención")
        constructor.setMessage("Desea cerrar sesión?").
        setPositiveButton("Aceptar") { dialog, id ->
            launch {
                withContext(Dispatchers.Main){
                    viewModel.cerrarsesion().await()
                }.run { finish() }
            }
        }.setNegativeButton("Cancelar") { dialog, id ->
        }
        constructor.setCancelable(false).show()
    }
}
