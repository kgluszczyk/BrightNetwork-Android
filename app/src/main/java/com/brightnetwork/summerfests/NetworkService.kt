package com.brightnetwork.summerfests

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object NetworkService {

    var moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val httpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val festivalService = retrofit.create(FestivalService::class.java)

    interface FestivalService {

        @GET("events")
        fun getFestivals(): Call<List<FestivalDTO>>
    }
}