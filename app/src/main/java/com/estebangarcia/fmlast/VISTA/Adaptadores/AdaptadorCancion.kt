package com.estebangarcia.fmlast.VISTA.Adaptadores

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopCanciones.EntidadCancion
import com.estebangarcia.fmlast.R

class AdaptadorCancion (lista:List<EntidadCancion>,limInf:Int): RecyclerView.Adapter<AdaptadorCancion.ViewHolder>() {

    var listathis=lista
    var limInf=limInf
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        lateinit var contexto:Context
        lateinit var botoncancion: Button
        lateinit var botonartista: Button
        @SuppressLint("ResourceAsColor")
        fun bindItems(item: EntidadCancion,position: Int){

            contexto=itemView.context
            val textorank: TextView =itemView.findViewById(R.id.textoRankingCancion)
            val imagen: ImageView =itemView.findViewById(R.id.imagenCancion)
            val textonombrecancion: TextView =itemView.findViewById(R.id.textoNombreCancion)
            val textoduracion:TextView =itemView.findViewById(R.id.textoDuracionCancion)
            val textooyentes: TextView =itemView.findViewById(R.id.textoOyentesCancion)
            val textotrasmitible: TextView =itemView.findViewById(R.id.textoTrasmitibleCancion)
            val textombidcancion: TextView =itemView.findViewById(R.id.textoMbidCancion)
             botoncancion =itemView.findViewById(R.id.botonVisitarCancion)

            val textonombreartista: TextView =itemView.findViewById(R.id.textoNombreArtistaC)
            val textombidartista: TextView =itemView.findViewById(R.id.textoMbidArtistaC)
            botonartista =itemView.findViewById(R.id.botonVisitarArtistaC)


            Glide.with(itemView.context).load(item.image).into(imagen)
            textorank.text=position.toString()
            textonombrecancion.text=item.nombrecancion
            textoduracion.text=item.duration
            textooyentes.text=item.listeners
            if(item.streamable.equals("0")){
                textotrasmitible.text="No"
            }else{textotrasmitible.text="Si"}
            textombidcancion.text=item.mbidcancion

            textonombreartista.text=item.nombreartista
            textombidartista.text=item.mbidartista
        }

    fun setOnClickListeners(item: EntidadCancion){
        botoncancion.setOnClickListener {
            val uri= Uri.parse(item.urlCancion)
            val intent= Intent(Intent.ACTION_VIEW,uri)
            contexto.startActivity(intent)
        }
        botonartista.setOnClickListener {
            val uri= Uri.parse(item.urlartista)
            val intent= Intent(Intent.ACTION_VIEW,uri)
            contexto.startActivity(intent)
        }
    }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemcancion,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=listathis[position]
        holder.bindItems(item,position+limInf)
        holder.setOnClickListeners(item)
    }

    override fun getItemCount(): Int {
        return listathis.size
    }


}