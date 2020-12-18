package com.example.projectandroid.datas

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectandroid.dataclass.Restaurant




class RestaurantDataViewMode(restaurant: Restaurant, app: Application):AndroidViewModel(app) {

    private val _selectedRestaurant = MutableLiveData<Restaurant>()
    val selectedRestaurant: LiveData<Restaurant>
        get() = _selectedRestaurant





    init {
        _selectedRestaurant.value = restaurant
    }

}