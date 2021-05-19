package com.brightnetwork.summerfests

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    var text = ""
    lateinit var imageToReplace : ImageView

    val getPictureFromCameraAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val image = result.data?.extras?.get("data") as Bitmap
            imageToReplace.setImageBitmap(image)
        }
    }

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
        imageToReplace = findViewById(R.id.imageView)
        val input = findViewById<EditText>(R.id.searchFestivalInputField)

        val searchButton = findViewById<Button>(R.id.searchForFestivals)
        searchButton.setOnClickListener {
            text = input.text.toString().take(3)
            Log.d("FESTFEST-1", "InputText:${text}: $this")
            val festivals = Datasource().loadFestivals(this)
            Log.d("FESTFEST-2", "Festivals:${festivals}")
        }

        setupCameraEntryPoint()
        setupSensors()
    }

    private fun setupSensors() {
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val label = "${event.sensor.name}: ${event.values[0]} cm"
                Log.d("SENSOR", label)
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            }

        }
        sensorManager.registerListener(listener, proximitySensor, SensorManager.SENSOR_DELAY_UI)
    }

    private fun setupCameraEntryPoint() {
        imageToReplace.setOnClickListener {
            getPictureFromCameraAction.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
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