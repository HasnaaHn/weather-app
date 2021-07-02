package com.hasnaadev.coding.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hasnaadev.coding.R
import com.hasnaadev.coding.models.WeatherResponse
import com.hasnaadev.coding.viewmodels.OpenWeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class WeatherActivity : AppCompatActivity() {

    private lateinit var listOfLoadingMessages: Array<String>
    private lateinit var listOfCities: Array<String>
    private val listOfWeathers = mutableListOf<WeatherResponse>()
    private val weatherAdapter: WeatherAdapter = WeatherAdapter()
    private var progression = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        listOfLoadingMessages = resources.getStringArray(R.array.loading_messages_array)
        listOfCities = resources.getStringArray(R.array.cities_array)

        initRecyclerView()

        CoroutineScope(IO).launch {
            launch {
                doStartProgressBar()
            }

            launch {
                displayMessagesInRow()
            }
        }

        weatherApiCall()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = weatherAdapter
    }

    private suspend fun doStartProgressBar() {
        withContext(Main) {
            while (progression < progressBar.max) {
                progression += 1
                delay(1000)
                progressBar.progress = progression
            }
            loadingText.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun weatherApiCall() {
        val viewModel = ViewModelProvider(this).get(OpenWeatherViewModel::class.java)
        viewModel.getWeatherList().observe(this, Observer{
            if (it != null) {
                weatherAdapter.setList(it)
            } else {
                Toast.makeText(baseContext, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.weatherListApiCall(listOfCities)
    }

    private suspend fun displayMessagesInRow() {
        withContext(Main) {
            for (loadingMessage in listOfLoadingMessages) {
                loadingText.text = loadingMessage.toString()
                delay(6000)
            }
        }
    }

}