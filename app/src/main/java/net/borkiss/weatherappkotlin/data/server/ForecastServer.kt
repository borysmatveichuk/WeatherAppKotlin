package net.borkiss.weatherappkotlin.data.server

import net.borkiss.weatherappkotlin.data.db.ForecastDb
import net.borkiss.weatherappkotlin.domain.datasource.ForecastDataSource
import net.borkiss.weatherappkotlin.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}