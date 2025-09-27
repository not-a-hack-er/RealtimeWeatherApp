package com.abpvt.realtimeweather1



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abpvt.realtimeweather1.api.Constant
import com.abpvt.realtimeweather1.api.NetworkResponse
import com.abpvt.realtimeweather1.api.RetrofitInstance
import kotlinx.coroutines.launch
import com.abpvt.realtimeweather1.api.WeatherModel

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        if (city.isBlank()) {
            _weatherResult.value = NetworkResponse.Error("City cannot be blank")
            return
        }
        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                Log.i("WeatherViewModel", "Making API call for city: $city")
                val response = weatherApi.getWeather(Constant.apiKey, city.trim())
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                        Log.i("WeatherViewModel", "Success: $it")
                    } ?: run {
                        _weatherResult.value = NetworkResponse.Error("Empty response body")
                        Log.i("WeatherViewModel", "Empty response body")
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("API Error: ${response.code()} ${response.message()}")
                    Log.i("WeatherViewModel", "API Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Exception: ${e.localizedMessage ?: "Unknown error"}")
                Log.e("WeatherViewModel", "Exception in API call", e)
            }
        }
    }
}
