package net.borkiss.weatherappkotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import net.borkiss.weatherappkotlin.domain.model.ForecastList

class ForecastListAdapter(val weekForecast: ForecastList) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }

    }

    override fun getItemCount(): Int {
        return weekForecast.dailyForecast.size
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}