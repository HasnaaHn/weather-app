package com.hasnaadev.coding.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasnaadev.coding.models.WeatherResponse
import com.hasnaadev.coding.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenWeatherViewModel : ViewModel() {

    var weatherListLiveData = MutableLiveData<List<WeatherResponse>>()
    var weatherLiveData = MutableLiveData<WeatherResponse>()
    var weatherList = mutableListOf<WeatherResponse>()

    fun getWeatherList() : MutableLiveData<List<WeatherResponse>> {
        return weatherListLiveData
    }

    fun weatherListApiCall(cityList: Array<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (city in cityList) {
                val cityResponse = RetrofitInstance.service.getWeatherResponse(city)
                weatherList.add(cityResponse)
            }
            weatherListLiveData.postValue(weatherList)
        }
    }
}