package com.brightnetwork.summerfests

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class Festival(
    val title: String,
    val date: String,
    val cost: String,
    val genres: String,
    val position: LatLng? = null,
    val imageUrl: String? = null
) : Parcelable

fun List<FestivalDTO>.toFestivals() = this.map { festivalDTO ->
    Festival(
        title = festivalDTO.name,
        date = "${festivalDTO.startDate}(${festivalDTO.durationInDays})",
        cost = "${festivalDTO.cost} ${festivalDTO.currency}",
        genres = festivalDTO.genre ?: "-",
        imageUrl = festivalDTO.imageUrl,
        position = if (festivalDTO.latitude == null || festivalDTO.longitude == null) null else LatLng(
            festivalDTO.latitude,
            festivalDTO.longitude
        )
    )
}

fun List<FestivalDTO>.getFestival(location: String) = this.filter {
    it.location == location
}.asSequence().sortedBy { it.cost ?: Int.MAX_VALUE }.map { it.cost }.take(3)
