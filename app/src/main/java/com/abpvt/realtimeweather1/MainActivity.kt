package com.abpvt.realtimeweather1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abpvt.realtimeweather1.ui.theme.RealtimeWeather1Theme
import androidx.lifecycle.ViewModelProvider
import com.abpvt.realtimeweather1.WeatherPage
import com.abpvt.realtimeweather1.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            RealtimeWeather1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
WeatherPage(weatherViewModel)
                }
            }
        }
    }
}

