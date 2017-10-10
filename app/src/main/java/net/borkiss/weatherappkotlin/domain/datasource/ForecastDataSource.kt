package net.borkiss.weatherappkotlin.domain.datasource

import net.borkiss.weatherappkotlin.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}