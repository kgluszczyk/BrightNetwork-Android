package com.brightnetwork.summerfests

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class FestivalDTO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "imageUrl") val imageUrl: String,
    @field:Json(name = "startDate") val startDate: String,
    @field:Json(name = "durationInDays") val durationInDays: Int,
    @field:Json(name = "location") val location: String,
    @field:Json(name = "cost") val cost: Int?,
    @field:Json(name = "currency") val currency: String?,
    @field:Json(name = "lat") val latitude: Double?,
    @field:Json(name = "long") val longitude: Double?,
    @field:Json(name = "genre") val genre: String?
)