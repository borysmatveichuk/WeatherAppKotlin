package net.borkiss.weatherappkotlin.domain.commands

import net.borkiss.weatherappkotlin.domain.datasource.ForecastProvider
import net.borkiss.weatherappkotlin.domain.model.Forecast

class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}