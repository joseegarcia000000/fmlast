package com.estebangarcia.fmlast.VISTA.Fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion
import com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment.CancionesViewModel
import com.estebangarcia.fmlast.MODELOVISTA.CancionesFragment.CancionesViewModelFactory

import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.VISTA.Adaptadores.AdaptadorCancion
import com.estebangarcia.fmlast.VISTA.Base.alcanceFragment
import com.estebangarcia.fmlast.databinding.FragmentCancionesBinding
import kotlinx.android.synthetic.main.fragment_canciones.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CancionesFragment : alcanceFragment() ,KodeinAware{

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory:CancionesViewModelFactory by instance()
    private  lateinit var viewModel:CancionesViewModel
    private var lista=listOf<EntidadCancion>()
    private var Intervalo=10
    private var LimSup:Int=Intervalo
    private var LimInf:Int=1
    private var sizelista:Int=1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=DataBindingUtil.inflate<FragmentCancionesBinding>(inflater,R.layout.fragment_canciones,container,false)
        (activity as AppCompatActivity)
        viewModel= ViewModelProvider(this,viewModelFactory).get(CancionesViewModel::class.java)
        onViewStateRestored(savedInstanceState)

        binding.textoPaginaInicio.text=LimInf.toString()
        binding.textoPaginacionActual.text=LimSup.toString()
        binding.textoPaginacionFinal.text=sizelista.toString()
        binding.recyclerCanciones.layoutManager= LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)

        binding.botonSiguiente.setOnClickListener{
            if(!lista.isEmpty()){
            if(lista.size>(LimSup+Intervalo)){
                LimInf=LimSup+1
                LimSup=LimSup+Intervalo

            }else{
                if(sizelista!=LimSup){
                    LimInf=LimSup
                    LimSup=sizelista
                }
            }
                actualizarPaginacion()
                asignarValoresRecycler(lista)
            }
        }
        binding.botonAnterior.setOnClickListener {
            if(!lista.isEmpty()){
            if(LimSup==sizelista){
                if(sizelista>1){
                    LimSup=LimInf
                    LimInf=LimInf-Intervalo+1
                }
            }else if(LimInf>1){
                LimSup=LimSup-Intervalo
                LimInf=LimInf-Intervalo
            }
                actualizarPaginacion()
                asignarValoresRecycler(lista)
            }
        }

        return binding.root
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.Main){
                val topcanciones=viewModel.obtenertopcanciones().await()
                topcanciones.observe(this@CancionesFragment as LifecycleOwner, Observer {
                    if(!(it.isEmpty())){
                        sizelista=it.size
                        lista=it
                        actualizarPaginacion()
                       asignarValoresRecycler(it)
                    }
                })
            }
        }.run {

        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("LI",LimInf)
        outState.putInt("LS",LimSup)
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            LimInf=savedInstanceState.getInt("LI")
        }
        if (savedInstanceState != null) {
            LimSup=savedInstanceState.getInt("LS")
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun actualizarPaginacion(){
        textoPaginaInicio.text=LimInf.toString()

        if(sizelista<10){
            textoPaginacionActual.text=sizelista.toString()
        }else{
            textoPaginacionActual.text=LimSup.toString()
        }
        textoPaginacionFinal.text=sizelista.toString()
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun asignarValoresRecycler(it:List<EntidadCancion>){
        recyclerCanciones.visibility=View.VISIBLE
        var adaptador:AdaptadorCancion
        if(LimSup<it.size){
            var sublist=it.subList(LimInf-1,LimSup)
            adaptador=AdaptadorCancion(sublist,limInf = LimInf)
            recyclerCanciones.adapter=adaptador
        }else if(it.size>=1){ adaptador=AdaptadorCancion(it,limInf = LimInf)
            recyclerCanciones.adapter=adaptador
        }
    }

}
