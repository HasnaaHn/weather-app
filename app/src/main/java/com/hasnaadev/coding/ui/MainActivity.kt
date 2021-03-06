package com.hasnaadev.coding.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hasnaadev.coding.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goToNextActivityButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, WeatherActivity::class.java))
        }
    }
}