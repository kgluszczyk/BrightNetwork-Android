package com.brightnetwork.summerfests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FestivalsActivity : AppCompatActivity() {

    val adapterRV = FestivalsAdapter { festival ->
        FestivalDetailsActivity.getIntent(this@FestivalsActivity, festival)
            .also {
                startActivity(it)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festivals)
        findViewById<RecyclerView>(R.id.festivals).apply {
            adapter = adapterRV
        }
        adapterRV.setData(Datasource().loadFestivals(this@FestivalsActivity))
        fetchFestivals()
    }

    private fun fetchFestivals() {
        val call = NetworkService.festivalService.getFestivals()
        call.enqueue(object : Callback<List<FestivalDTO>> {
            override fun onResponse(call: Call<List<FestivalDTO>>, response: Response<List<FestivalDTO>>) {
                Log.d("RETROFIT", "Response: ${response.body()}")

                response.body()?.map { festivalDTO ->
                    Festival(
                        title = festivalDTO.name,
                        date = "${festivalDTO.startDate}(${festivalDTO.durationInDays})",
                        cost = "${festivalDTO.cost} ${festivalDTO.currency}",
                        genres = festivalDTO.genre ?: "-",
                        imageUrl = festivalDTO.imageUrl
                    )
                }?.let {
                    adapterRV.setData(it)
                }
            }

            override fun onFailure(call: Call<List<FestivalDTO>>, t: Throwable) {
                Log.e("RETROFIT", "Failed to fetch festivals", t)
            }

        })
    }
}