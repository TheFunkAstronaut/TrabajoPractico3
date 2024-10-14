package com.example.trabajopractico3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico3.R
import com.example.trabajopractico3.modelos.Like

class LikesAdapter : ListAdapter<Like, LikesAdapter.LikesViewHolder>(LikesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_like, parent, false)
        return LikesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val like = getItem(position)
        holder.bind(like)
    }

    class LikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        private val imageViewFoto: ImageView = itemView.findViewById(R.id.imageViewFoto)

        fun bind(like: Like) {
            textViewNombre.text = like.nombre
            imageViewFoto.setImageResource(like.foto)
        }
    }

    class LikesDiffCallback : DiffUtil.ItemCallback<Like>() {
        override fun areItemsTheSame(oldItem: Like, newItem: Like): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Like, newItem: Like): Boolean {
            return oldItem == newItem
        }
    }
}
