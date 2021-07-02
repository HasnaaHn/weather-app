package com.hasnaadev.coding.network

import com.hasnaadev.coding.models.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "5c1e1f018d380cf155a45855fd56e58d"

interface OpenWeatherMapService {

    @GET("data/2.5/weather?units=metric&lang=fr")
    suspend fun getWeatherResponse(@Query("q") city: String,
                           @Query("appid") api: String = API_KEY): WeatherResponse
}

