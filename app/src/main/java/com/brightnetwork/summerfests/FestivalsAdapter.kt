package com.brightnetwork.summerfests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FestivalsAdapter(private val festivals: List<Festival>) : RecyclerView.Adapter<FestivalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalViewHolder {
        return FestivalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.festival_item, parent, false))
    }

    override fun onBindViewHolder(holder: FestivalViewHolder, position: Int) {
        holder.bind(festivals[position])
    }

    override fun getItemCount(): Int {
        return festivals.size
    }
}

class FestivalViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(festival: Festival) {
        view.findViewById<TextView>(R.id.title).text = festival.title
        view.findViewById<TextView>(R.id.cost).text = festival.cost
        view.findViewById<TextView>(R.id.date).text = festival.date
        view.findViewById<TextView>(R.id.genres).text = festival.genres
    }

}