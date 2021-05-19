package com.brightnetwork.summerfests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

typealias FestivalClick = (Festival) -> Unit
class FestivalsAdapter(private val festivals: List<Festival>, private val action: FestivalClick) : RecyclerView.Adapter<FestivalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalViewHolder {
        return FestivalViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.festival_item, parent, false),
            action = action
        )
    }

    override fun onBindViewHolder(holder: FestivalViewHolder, position: Int) {
        holder.bind(festivals[position])
    }

    override fun getItemCount(): Int {
        return festivals.size
    }
}

class FestivalViewHolder(private val view: View, private val action: FestivalClick) :
    RecyclerView.ViewHolder(view) {

    fun bind(festival: Festival) {
        view.findViewById<ImageView>(R.id.image_background).setImageResource(festival.image)
        view.findViewById<TextView>(R.id.title).text = festival.title
        view.findViewById<TextView>(R.id.cost).text = festival.cost
        view.findViewById<TextView>(R.id.date).text = festival.date
        view.findViewById<TextView>(R.id.genres).text = festival.genres
        view.setOnClickListener {
            action(festival)
        }
    }

}