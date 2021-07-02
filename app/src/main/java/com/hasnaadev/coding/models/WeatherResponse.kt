package com.hasnaadev.coding.models

import com.google.gson.annotations.SerializedName

data class  WeatherResponse(val weather: List<WeatherData>,
                            val main: MainData,
                            val name: String)

data class WeatherData(val description: String,
                       val icon: String)

data class MainData(@SerializedName("temp") val temperature: Float)