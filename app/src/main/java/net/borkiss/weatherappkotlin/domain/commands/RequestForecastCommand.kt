package net.borkiss.weatherappkotlin.domain.commands

import net.borkiss.weatherappkotlin.domain.datasource.ForecastProvider
import net.borkiss.weatherappkotlin.domain.model.ForecastList

class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()): Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}