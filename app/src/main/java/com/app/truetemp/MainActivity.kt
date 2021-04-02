package com.app.truetemp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background = findViewById<ImageView>(R.id.background)
        val icon = findViewById<ImageView>(R.id.icon)
        val location = findViewById<TextView>(R.id.location)
        val weather = findViewById<TextView>(R.id.weather)
        val temperature = findViewById<TextView>(R.id.temperature)

        val dayOneIcon = findViewById<ImageView>(R.id.dayOneIcon)
        val dayTwoIcon = findViewById<ImageView>(R.id.dayTwoIcon)
        val dayThreeIcon = findViewById<ImageView>(R.id.dayThreeIcon)
        val dayFourIcon = findViewById<ImageView>(R.id.dayFourIcon)
        val dayFiveIcon = findViewById<ImageView>(R.id.dayFiveIcon)
        val daySixIcon = findViewById<ImageView>(R.id.daySixIcon)
        val daySevenIcon = findViewById<ImageView>(R.id.daySevenIcon)

        val dayOneDate = findViewById<TextView>(R.id.dayOneDate)
        val dayTwoDate = findViewById<TextView>(R.id.dayTwoDate)
        val dayThreeDate = findViewById<TextView>(R.id.dayThreeDate)
        val dayFourDate = findViewById<TextView>(R.id.dayFourDate)
        val dayFiveDate = findViewById<TextView>(R.id.dayFiveDate)
        val daySixDate = findViewById<TextView>(R.id.daySixDate)
        val daySevenDate = findViewById<TextView>(R.id.daySevenDate)

        val dayOneTemp = findViewById<TextView>(R.id.dayOneTemp)
        val dayTwoTemp = findViewById<TextView>(R.id.dayTwoTemp)
        val dayThreeTemp = findViewById<TextView>(R.id.dayThreeTemp)
        val dayFourTemp = findViewById<TextView>(R.id.dayFourTemp)
        val dayFiveTemp = findViewById<TextView>(R.id.dayFiveTemp)
        val daySixTemp = findViewById<TextView>(R.id.daySixTemp)
        val daySevenTemp = findViewById<TextView>(R.id.daySevenTemp)





        //sample code
        background.setImageResource(R.drawable.stormbg)
        icon.setImageResource(R.drawable.rainy)
        location.setText("Dearborn, MI")
        weather.setText("Rain")
        temperature.setText("50°")

        dayTwoDate.setText("Thursday")
        dayTwoIcon.setImageResource(R.drawable.storm)
        dayTwoTemp.setText("70°")




    }

}