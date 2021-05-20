package com.brightnetwork.summerfests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FestivalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festivals)
        findViewById<RecyclerView>(R.id.festivals).apply {
            adapter = FestivalsAdapter(Datasource().loadFestivals(this@FestivalsActivity)) { festival ->
                FestivalDetailsActivity.getIntent(this@FestivalsActivity, festival)
                    .also {
                        startActivity(it)
                    }
            }
        }
        fetchFestivals()
    }

    private fun fetchFestivals() {
        val call = NetworkService.festivalService.getFestivals()
        call.enqueue(object : Callback<List<FestivalDTO>> {
            override fun onResponse(call: Call<List<FestivalDTO>>, response: Response<List<FestivalDTO>>) {
                Log.d("RETROFIT", "Response: ${response.body()}")
            }

            override fun onFailure(call: Call<List<FestivalDTO>>, t: Throwable) {
                Log.e("RETROFIT", "Failed to fetch festivals", t)
            }

        })
    }
}