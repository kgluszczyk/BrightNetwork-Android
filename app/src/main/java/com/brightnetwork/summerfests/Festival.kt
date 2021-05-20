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