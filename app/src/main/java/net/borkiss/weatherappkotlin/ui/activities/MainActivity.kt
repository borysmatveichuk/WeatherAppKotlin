package net.borkiss.weatherappkotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.borkiss.weatherappkotlin.R
import net.borkiss.weatherappkotlin.domain.commands.RequestForecastCommand
import net.borkiss.weatherappkotlin.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(1000).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result,
                        { toast(it.description) })
            }
        }
    }
}
