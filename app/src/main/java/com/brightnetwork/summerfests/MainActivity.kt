package com.brightnetwork.summerfests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    var text = ""

    companion object {
        const val textKey = "TEXT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        text = savedInstanceState?.getString(textKey) ?: text
        Log.d("SINGLETON", Singleton.name)
        Log.d("LIFECYCLE", "onCreate")
        Log.d("FESTFEST-0", "InputText:${text}")
        setContentView(R.layout.activity_main)
        val input = findViewById<EditText>(R.id.searchFestivalInputField)

        val searchButton = findViewById<Button>(R.id.searchForFestivals)
        searchButton.setOnClickListener {
            text = input.text.toString().take(3)
            Log.d("FESTFEST-1", "InputText:${text}: $this")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(textKey, text)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy")
    }
}