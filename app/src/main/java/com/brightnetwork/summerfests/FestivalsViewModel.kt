package com.brightnetwork.summerfests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val festivalsDTO = NetworkService.festivalService.getFestivals()
                Log.d("RETROFIT", "Response: $festivalsDTO")
                festivalsDTO.let {
                    App.database.festivalsDao().delete()
                    App.database.festivalsDao().insert(festivalsDTO)
                }
                festivalsDTO.let {
                    dataStream.postValue(FestivalsState.Loaded(it.toFestivals()))
                }
            } catch (exception: Exception) {
                Log.e("RETROFIT", "Failed to fetch festivals", exception)
                dataStream.postValue(FestivalsState.Error)
                App.database.festivalsDao().get().also {
                    dataStream.postValue(FestivalsState.Loaded(it.toFestivals()))
                }
            }
        }
    }

    sealed class FestivalsState {
        object Loading : FestivalsState()
        data class Loaded(val list: List<Festival>) : FestivalsState()
        object Error : FestivalsState()
    }
}