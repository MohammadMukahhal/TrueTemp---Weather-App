package com.app.truetemp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var background: ImageView
    private lateinit var icon: ImageView
    private lateinit var location: TextView
    private lateinit var weather: TextView
    private lateinit var temperature: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        background = findViewById(R.id.background)
        icon = findViewById(R.id.icon)
        location = findViewById(R.id.location)
        weather=findViewById(R.id.weather)
        temperature=findViewById(R.id.temperature)




        //sample code
        background.setImageResource(R.drawable.stormbg)
        icon.setImageResource(R.drawable.rainy)
        location.setText("Dearborn, MI")
        weather.setText("Rain")
        temperature.setText("50°")


    }
}