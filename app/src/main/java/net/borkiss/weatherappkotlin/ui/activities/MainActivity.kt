package net.borkiss.weatherappkotlin.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
//import kotlinx.android.synthetic.main.activity_main.*
import net.borkiss.weatherappkotlin.R
import net.borkiss.weatherappkotlin.data.ForecastRequest
import net.borkiss.weatherappkotlin.domain.commands.RequestForecastCommand
import net.borkiss.weatherappkotlin.domain.model.Forecast
import net.borkiss.weatherappkotlin.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.*

private val items = listOf(
    "Mon 6/23 - Sunny - 31/17",
    "Tue 6/24 - Foggy - 21/8",
    "Wed 6/25 - Cloudy - 22/17",
    "Thurs 6/26 - Rainy - 18/11",
    "Fri 6/27 - Foggy - 21/10",
    "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
    "Sun 6/29 - Sunny - 20/7"
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = find<RecyclerView> (R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("01000").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { toast(it.date) }
            }
        }
    }
}
