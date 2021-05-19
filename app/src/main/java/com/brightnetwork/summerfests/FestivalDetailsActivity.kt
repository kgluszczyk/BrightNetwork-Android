package com.brightnetwork.summerfests

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.lang.IllegalStateException

class FestivalDetailsActivity : AppCompatActivity() {

    companion object {

        const val festivalKey = "FestivalKey"
        fun getIntent(context: Context, festival: Festival) = Intent(
            context,
            FestivalDetailsActivity::class.java
        ).apply {
            putExtra(festivalKey, festival)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val festival = intent.extras?.get(festivalKey) ?: throw IllegalStateException("Did you forget to pass the festival?")
        Toast.makeText(this, festival.toString(), Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_festival_details)
    }
}