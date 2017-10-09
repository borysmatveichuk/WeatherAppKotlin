package net.borkiss.weatherappkotlin.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastRequest(private val zipCode: Long) {

    companion object {
        private val APP_ID = "46c8b7efcac89c7a3f1b4e463e6fe010"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        //http://api.openweathermap.org/data/2.5/forecast/daily?" +
        //"APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${URL}&APPID=${APP_ID}&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}