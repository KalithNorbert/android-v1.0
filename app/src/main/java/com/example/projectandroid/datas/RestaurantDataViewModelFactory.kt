package com.example.projectandroid.datas


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.dataclass.Restaurant
import com.example.projectandroid.dataclass.RestaurantsClass


//úgy tudunk értéket átadni a ViewModelnek
class RestaurantDataViewModelFactory (
    private val restaurant: Restaurant,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RestaurantDataViewMode::class.java)) {
                return RestaurantDataViewMode(restaurant, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}