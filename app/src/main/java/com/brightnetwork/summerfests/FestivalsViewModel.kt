package com.brightnetwork.summerfests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FestivalsViewModel : ViewModel() {

    private val dataStream = MutableLiveData<FestivalsState>()
    val _dataStream: LiveData<FestivalsState> = dataStream

    init {
        fetchFestivals()
    }

    private fun fetchFestivals() {
        dataStream.postValue(FestivalsState.Loading)
        val call = NetworkService.festivalService.getFestivals()
        call.enqueue(object : Callback<List<FestivalDTO>> {
            override fun onResponse(call: Call<List<FestivalDTO>>, response: Response<List<FestivalDTO>>) {
                val festivalsDTO = response.body()
                Log.d("RETROFIT", "Response: $festivalsDTO")
                festivalsDTO?.let {
                    Thread {
                        App.database.festivalsDao().delete()
                        App.database.festivalsDao().insert(festivalsDTO)
                    }.start()
                }
                festivalsDTO?.map { festivalDTO ->
                    Festival(
                        title = festivalDTO.name,
                        date = "${festivalDTO.startDate}(${festivalDTO.durationInDays})",
                        cost = "${festivalDTO.cost} ${festivalDTO.currency}",
                        genres = festivalDTO.genre ?: "-",
                        imageUrl = festivalDTO.imageUrl
                    )
                }?.let {
                    dataStream.postValue(FestivalsState.Loaded(it))
                }
            }

            override fun onFailure(call: Call<List<FestivalDTO>>, t: Throwable) {
                Log.e("RETROFIT", "Failed to fetch festivals", t)
                dataStream.postValue(FestivalsState.Error)
            }

        })
    }

    sealed class FestivalsState {
        object Loading : FestivalsState()
        data class Loaded(val list: List<Festival>) : FestivalsState()
        object Error : FestivalsState()
    }
}