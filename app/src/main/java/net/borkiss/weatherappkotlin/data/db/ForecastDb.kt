package net.borkiss.weatherappkotlin.data.db

import net.borkiss.weatherappkotlin.domain.datasource.ForecastDataSource
import net.borkiss.weatherappkotlin.domain.model.ForecastList
import net.borkiss.weatherappkotlin.extensions.clear
import net.borkiss.weatherappkotlin.extensions.parseList
import net.borkiss.weatherappkotlin.extensions.parseOpt
import net.borkiss.weatherappkotlin.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                                   private val dataMapper: DbDataMapper = DbDataMapper()): ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

                val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
                val dailyForecast = select(DayForecastTable.NAME)
                        .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                        .parseList { DayForecast(HashMap(it)) }

                val city = select(CityForecastTable.NAME)
                        .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                        .parseOpt { CityForecast(HashMap(it), dailyForecast) }

                if (city != null) dataMapper.convertToDomain(city) else null
            }

        fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

                clear(CityForecastTable.NAME)
                clear(DayForecastTable.NAME)

                with(dataMapper.convertFromDomain(forecast)) {
                        insert(CityForecastTable.NAME, *map.toVarargArray())
                        dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
                    }
            }
}