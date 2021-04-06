package com.app.truetemp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val CITY: String = "Dearborn"
    val API: String = "23408758b6f96d1e9dacebfd604a7240"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
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

        /*
        //sample code
        background.setImageResource(R.drawable.stormbg)
        icon.setImageResource(R.drawable.rainy)
        location.setText("Dearborn, MI")
        weather.setText("Rain")
        temperature.setText("50°")

        dayTwoDate.setText("Thursday")
        dayTwoIcon.setImageResource(R.drawable.storm)
        dayTwoTemp.setText("70°")

         */

        weatherTask().execute()
    }
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        var responseDaily: String? = null
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?

            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$API").readText(
                        Charsets.UTF_8
                )
                responseDaily = URL("https://api.openweathermap.org/data/2.5/onecall?lat=42.3223&lon=-83.1763&exclude=hourly,minutely,alerts&appid=$API&units=metric").readText(
                    Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
                responseDaily = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val temp = main.getString("temp")
                val tempMin = "Min Temp: " + main.getString("temp_min")
                val tempMax = "Max Temp: " + main.getString("temp_max")
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")
                val icon = weather.getString("icon")

                //Updating UI
                findViewById<TextView>(R.id.weather).text = weatherDescription
                findViewById<TextView>(R.id.location).text = address
                //Calc temp
                val tempF:Int = (((temp.toDouble() - 273.15) * 9/5 + 32).toInt())
                findViewById<TextView>(R.id.temperature).text = tempF.toString() + "°F"

                changeImage(icon)




            } catch (e: Exception) {

            }
        }

        private fun changeImage(icon: String) {
            val background = findViewById<ImageView>(R.id.background)
            val iconUI = findViewById<ImageView>(R.id.icon)
            //Set BG
            if(icon == "01d" || icon == "02d" || icon == "01n" || icon == "02n"){
                background.setImageResource(R.drawable.sunnybg)
                if(icon.get(2) == 'n') {
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    iconUI.setImageResource(R.drawable.sun)
                }
            }
            else if(icon == "09d" || icon == "10d" || icon == "09n" || icon == "10n") {
                background.setImageResource(R.drawable.rainybg)
                iconUI.setImageResource(R.drawable.rainy)
            }
            else if(icon == "11d" || icon == "11n") {
                background.setImageResource(R.drawable.stormbg)
                iconUI.setImageResource(R.drawable.storm)
            }
            else{
                background.setImageResource(R.drawable.nightbg)
                if(icon.get(2) == 'n') {
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    iconUI.setImageResource(R.drawable.cloud)
                }
            }



        }
    }
}

