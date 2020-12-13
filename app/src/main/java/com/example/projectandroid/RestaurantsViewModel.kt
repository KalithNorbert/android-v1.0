package com.example.projectandroid

import android.util.Log
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
import java.lang.Exception

class RestaurantsViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status



    private val _resInfo = MutableLiveData<List<Restaurant>>()
    val resInfo: LiveData<List<Restaurant>>
        get() = _resInfo



    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    init {
        getRestaurantsProperties()
    }

    private fun getRestaurantsProperties() {
        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()
            try {
              //  Log.d("Korte", "msg" )
                val listResult = getInformation.await()

                if (listResult.restaurants.isNotEmpty()){
                    _resInfo.value = listResult.restaurants
                }
                 // _status.value = "Success: ${listResult.restaurants.size}  restaurants are here"
            }catch (t: Exception){
                _status.value = "Failure: "+ t.message
               // Log.d("Hiba", "mdddddddsg" )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

