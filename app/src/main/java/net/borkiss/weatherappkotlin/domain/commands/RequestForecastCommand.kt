package net.borkiss.weatherappkotlin.domain.commands

import net.borkiss.weatherappkotlin.data.server.ForecastRequest
import net.borkiss.weatherappkotlin.domain.mappers.ForecastDataMapper
import net.borkiss.weatherappkotlin.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String): Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}