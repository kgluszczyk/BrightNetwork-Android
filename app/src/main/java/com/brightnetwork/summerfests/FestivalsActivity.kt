package com.brightnetwork.summerfests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class FestivalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festivals)
        findViewById<RecyclerView>(R.id.festivals).apply {
            adapter = FestivalsAdapter(Datasource().loadFestivals(this@FestivalsActivity))
        }
    }
}