package com.brightnetwork.summerfests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

    lateinit var viewModel: FestivalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FestivalsViewModel::class.java)
        setContentView(R.layout.activity_festivals)
        findViewById<RecyclerView>(R.id.festivals).apply {
            adapter = adapterRV
        }
        adapterRV.setData(Datasource().loadFestivals(this@FestivalsActivity))

        viewModel._dataStream.observe(this) { festivalsState ->
            when(festivalsState){
                FestivalsViewModel.FestivalsState.Loading -> Toast.makeText(this, "Loading!", Toast.LENGTH_SHORT).show()
                is FestivalsViewModel.FestivalsState.Loaded -> adapterRV.setData(festivalsState.list)
                FestivalsViewModel.FestivalsState.Error -> Toast.makeText(this, "ERRROR!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}