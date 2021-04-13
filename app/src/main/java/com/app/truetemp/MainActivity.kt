package com.app.truetemp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.util.Calendar
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
        override fun onPreExecute() {

            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/onecall?lat=42.3223&lon=-83.1763&exclude=hourly,alerts,minutely&appid=$API&units=imperial").readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)

                val current = jsonObj.getJSONObject("current")

                val weather = current.getJSONArray("weather").getJSONObject(0)
                val temp = current.getString("temp")
                val weatherDescription = weather.getString("main")
                val icon = weather.getString("icon")

                //Updating UI
                findViewById<TextView>(R.id.weather).text = weatherDescription
                //Calc current temp
                findViewById<TextView>(R.id.temperature).text = temp.substringBefore(".") + "°F"
                changeImage(icon)

                val dateList = makeDate()
                //Calc 7 day temp
                val jsonObjArray = JSONObject(result).getJSONArray("daily")
                //First day
                val firstDay =  jsonObjArray.getJSONObject(0)
                val weather1 = firstDay.getJSONArray("weather").getJSONObject(0)
                val firstIcon = weather1.getString("icon")
                val firstTemp = firstDay.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.dayOneTemp).text = firstTemp.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.dayOneIcon).setImageResource(changeImageMini(firstIcon))
                findViewById<TextView>(R.id.dayOneDate).text = dateList[0]
                //Second day
                val day2 =  jsonObjArray.getJSONObject(1)
                val weather2 = day2.getJSONArray("weather").getJSONObject(0)
                val icon2 = weather2.getString("icon")
                val temp2 = day2.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.dayTwoTemp).text = temp2.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.dayTwoIcon).setImageResource(changeImageMini(icon2))
                findViewById<TextView>(R.id.dayTwoDate).text = dateList[1]
                //Third day
                val day3 =  jsonObjArray.getJSONObject(2)
                val weather3 = day3.getJSONArray("weather").getJSONObject(0)
                val icon3 = weather3.getString("icon")
                val temp3 = day3.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.dayThreeTemp).text = temp3.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.dayThreeIcon).setImageResource(changeImageMini(icon3))
                findViewById<TextView>(R.id.dayThreeDate).text = dateList[2]
                //Fourth day
                val day4 =  jsonObjArray.getJSONObject(3)
                val weather4 = day4.getJSONArray("weather").getJSONObject(0)
                val icon4 = weather4.getString("icon")
                val temp4 = day4.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.dayFourTemp).text = temp4.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.dayFourIcon).setImageResource(changeImageMini(icon4))
                findViewById<TextView>(R.id.dayFourDate).text = dateList[3]
                //Fifth day
                val day5 =  jsonObjArray.getJSONObject(4)
                val weather5 = day5.getJSONArray("weather").getJSONObject(0)
                val icon5 = weather5.getString("icon")
                val temp5 = day5.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.dayFiveTemp).text = temp5.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.dayFiveIcon).setImageResource(changeImageMini(icon5))
                findViewById<TextView>(R.id.dayFiveDate).text = dateList[4]
                //Sixth day
                val day6 =  jsonObjArray.getJSONObject(5)
                val weather6 = day6.getJSONArray("weather").getJSONObject(0)
                val icon6 = weather6.getString("icon")
                val temp6 = day6.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.daySixTemp).text = temp6.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.daySixIcon).setImageResource(changeImageMini(icon6))
                findViewById<TextView>(R.id.daySixDate).text = dateList[5]
                //Seventh day
                val day7 =  jsonObjArray.getJSONObject(6)
                val weather7 = day7.getJSONArray("weather").getJSONObject(0)
                val icon7 = weather7.getString("icon")
                val temp7 = day7.getJSONObject("temp").getString("day")
                findViewById<TextView>(R.id.daySevenTemp).text = temp7.substringBefore(".") + "°F"
                findViewById<ImageView>(R.id.daySevenIcon).setImageResource(changeImageMini(icon7))
                findViewById<TextView>(R.id.daySevenDate).text = dateList[6]






            } catch (e: Exception) {

            }
        }

        private fun changeImageMini(icon: String):Int {
            val icon = " "
            if(icon == "01d" || icon == "02d" || icon == "01n" || icon == "02n"){
                return R.drawable.sun
            }
            else if(icon == "09d" || icon == "10d" || icon == "09n" || icon == "10n") {
                return (R.drawable.rainy)
            }
            else if(icon == "11d" || icon == "11n") {
                return (R.drawable.storm)
            }
            else{
                return (R.drawable.cloud)
            }
        }

        private fun changeImage(icon: String) {
            val background = findViewById<ImageView>(R.id.background)
            val iconUI = findViewById<ImageView>(R.id.icon)
            //Set BG
            if(icon == "01d" || icon == "02d" || icon == "01n" || icon == "02n"){
                if(icon.get(2) == 'n') {
                    background.setImageResource(R.drawable.nightbg)
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    background.setImageResource(R.drawable.sunnybg)
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
                    background.setImageResource(R.drawable.nightbg)
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    iconUI.setImageResource(R.drawable.cloud)
                }
            }
        }

        private fun makeDate():List<String>{
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_WEEK)

            if(day == 1){
                return listOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY")
            }
            else if(day == 2){
                return listOf("TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY")
            }
            else if(day == 3){
                return listOf("WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY")
            }
            else if(day == 4){
                return listOf("THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY")
            }
            else if(day == 5){
                return listOf("FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY")
            }
            else if(day == 6){
                return listOf("SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY")
            }
            else if(day == 7){
                return listOf("SUNDAY","SATURDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY")
            }
            return listOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY")
        }

    }
}

