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
import com.estebangarcia.fmlast.MODELO.BD_persistencia.Entidades.GeoTopArtistas.EntidadArtista
import com.estebangarcia.fmlast.R


class AdaptadorArtistas (lista:List<EntidadArtista>,limInf:Int): RecyclerView.Adapter<AdaptadorArtistas.ViewHolder>() {

    var listathis=lista
    var limInf=limInf

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        lateinit var contexto: Context
        lateinit var botonartista: Button
        @SuppressLint("ResourceAsColor")
        fun bindItems(item: EntidadArtista,position: Int){

            contexto=itemView.context
            val textorank:TextView=itemView.findViewById(R.id.textoRankingArtista)
            val imagen: ImageView =itemView.findViewById(R.id.imagenArtista)
            val textonombre: TextView =itemView.findViewById(R.id.textoNombreArtistaC)
            val textooyentes:TextView=itemView.findViewById(R.id.textoOyentesArtista)
            val textotrasmitible:TextView=itemView.findViewById(R.id.textoTrasmitibleArtista)
            val textombid: TextView =itemView.findViewById(R.id.textoMbidArtistaC)
            botonartista=itemView.findViewById(R.id.botonVisitarArtista)

            Glide.with(itemView.context).load(item.image).into(imagen)
            textorank.text=position.toString()
            textonombre.text=item.name
            textooyentes.text=item.listeners
            if(item.streamable.equals("0")){
                textotrasmitible.text="No"
            }else{textotrasmitible.text="Si"}
            textombid.text=item.mbid
    }
        fun setOnClickListeners(item: EntidadArtista){
            botonartista.setOnClickListener {
                val uri= Uri.parse(item.url)
                val intent= Intent(Intent.ACTION_VIEW,uri)
                contexto.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemartista,parent,false)
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