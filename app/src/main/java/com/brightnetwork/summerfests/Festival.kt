package com.brightnetwork.summerfests

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Festival(
    val title: String,
    val date: String,
    val cost: String,
    val genres: String,
    val imageUrl: String? = null
) : Parcelable

fun List<FestivalDTO>.toFestivals() = this.map { festivalDTO ->
    Festival(
        title = festivalDTO.name,
        date = "${festivalDTO.startDate}(${festivalDTO.durationInDays})",
        cost = "${festivalDTO.cost} ${festivalDTO.currency}",
        genres = festivalDTO.genre ?: "-",
        imageUrl = festivalDTO.imageUrl
    )
}