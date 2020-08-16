package com.estebangarcia.fmlast.VISTA.Fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.estebangarcia.fmlast.R
import com.estebangarcia.fmlast.databinding.FragmentCancionesBinding
import kotlinx.android.synthetic.main.fragment_canciones.*

class CancionesFragment : Fragment() {

    private var LimSup:Int=50
    private var LimInf:Int=0
    private var sizelista:Int=34520

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=DataBindingUtil.inflate<FragmentCancionesBinding>(inflater,R.layout.fragment_canciones,container,false)
        (activity as AppCompatActivity)

        binding.textoPaginaInicio.text=LimInf.toString()
        binding.textoPaginacionActual.text=LimSup.toString()
        binding.textoPaginacionFinal.text=sizelista.toString()

        binding.botonSiguiente.setOnClickListener{
            if(sizelista>LimSup){
                LimSup=LimSup+50
                LimInf=LimInf+50
            }
            actualizarPaginacion()
        }
        binding.botonAnterior.setOnClickListener {
            if(LimInf>=0){
                LimSup=LimSup-50
                LimInf=LimInf-50
            }
            actualizarPaginacion()
        }

        return binding.root
    }

    private fun actualizarPaginacion(){
        textoPaginaInicio.text=LimInf.toString()
        textoPaginacionActual.text=LimSup.toString()
        textoPaginacionFinal.text=sizelista.toString()
    }
}
