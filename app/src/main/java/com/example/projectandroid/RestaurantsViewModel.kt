package com.example.projectandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.dataclass.Restaurant
import com.example.projectandroid.dataclass.RestaurantsClass
import com.example.projectandroid.network.RestaurantsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    init {
        getRestaurantsProperties()
    }

    private fun getRestaurantsProperties() {
        coroutineScope.launch {
            var getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
                var listResult = getInformation.await()
                _response.value = "Success: ${listResult.restaurants.size}  restaurants are here"
            }catch (t:Throwable){
                _response.value = "Failure: "+ t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

