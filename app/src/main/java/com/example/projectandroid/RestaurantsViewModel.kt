package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.network.RestaurantsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getRestaurantsProperties()
    }

    private fun getRestaurantsProperties() {
        RestaurantsApi.retrofitService.getProperties().enqueue(object: Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: "+ t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

        })
        _response.value = "Set the restaurants API here!"
    }
}