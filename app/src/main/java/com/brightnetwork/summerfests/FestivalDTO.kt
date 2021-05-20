package com.brightnetwork.summerfests

import com.squareup.moshi.Json

data class FestivalDTO(
    @field:Json(name= "name") val name: String,
    @field:Json(name= "imageUrl") val imageUrl: String,
    @field:Json(name= "startDate") val startDate: String,
    @field:Json(name= "durationInDays") val durationInDays: Int,
    @field:Json(name= "location") val location: String,
    @field:Json(name= "cost") val cost: Int?,
    @field:Json(name= "currency") val currency: String?,
    @field:Json(name= "genre") val genre: String?
)