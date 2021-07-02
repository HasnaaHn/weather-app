package com.hasnaadev.coding.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hasnaadev.coding.R
import com.hasnaadev.coding.models.WeatherResponse
import java.util.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var weatherList = listOf<WeatherResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        var weatherDataAtPosition = weatherList[position].weather.first()

        holder.weatherCityName.text = weatherList[position].name
        holder.weatherDescription.text = weatherDataAtPosition.description
            .replaceFirstChar { it.uppercase() }
        holder.weatherTemperature.text = String.format(
            context.resources.getString(R.string.weather_temperature),
            weatherList[position].main.temperature.toInt())
        Glide.with(context)
            .load("https://openweathermap.org/img/wn/${weatherDataAtPosition.icon}.png")
            .into(holder.weatherIcon)
    }

    override fun getItemCount(): Int = weatherList.size

    fun setList(list : List<WeatherResponse>) {
        this.weatherList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
        val weatherCityName: TextView = itemView.findViewById(R.id.weatherCityName)
        val weatherDescription: TextView = itemView.findViewById(R.id.weatherDescription)
        val weatherTemperature: TextView = itemView.findViewById(R.id.weatherTemperature)
    }

}