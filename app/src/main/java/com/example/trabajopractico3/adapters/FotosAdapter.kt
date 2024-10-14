package com.example.trabajopractico3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajopractico3.R

class FotosAdapter(private val fotos: List<Int>) : RecyclerView.Adapter<FotosAdapter.FotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_foto, parent, false)
        return FotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val fotoResId = fotos[position]
        Glide.with(holder.itemView.context)
            .load(fotoResId)  // Usamos el ID del recurso drawable
            .into(holder.imageViewFoto)
    }

    override fun getItemCount(): Int = fotos.size

    inner class FotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFoto: ImageView = itemView.findViewById(R.id.imageViewFoto)
    }
}
