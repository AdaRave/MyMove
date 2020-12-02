package com.example.mymove.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymove.R
import com.example.mymove.model.rotation.Results


class FilmAdapter (var items: List<Results>, var context: AppCompatActivity,
                    var clickListener: OnItemClickedListener) :RecyclerView.Adapter<FilmAdapter.FilmsHolder>(){


    inner class FilmsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val title = itemView.findViewById<TextView>(R.id.titleTextView)
        private val date = itemView.findViewById<TextView>(R.id.DateTextView)
        private val poster = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(item: Results, action : OnItemClickedListener){
            title.text = item.title
            date.text = item.release_date

            val imagePath = "https://image.tmdb.org/t/p/w500" + item.poster_path
            Glide.with(context).load(imagePath)
                .placeholder(R.drawable.ic_baseline_remove_from_queue_24)
                .into(poster)

            itemView.setOnClickListener {
                action.onItemClicked(item, adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsHolder =
        FilmsHolder(LayoutInflater.from(parent.context).
            inflate(R.layout.card_films_rated, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FilmsHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    interface OnItemClickedListener{
        fun onItemClicked(item: Results, position: Int)
    }
}