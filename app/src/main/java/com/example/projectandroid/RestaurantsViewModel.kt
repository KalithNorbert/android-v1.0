package com.example.projectandroid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectandroid.dataclass.Restaurant
import com.example.projectandroid.dataclass.RestaurantsClass
import com.example.projectandroid.network.RestaurantsApi
import kotlinx.coroutines.*
import java.lang.Exception

enum class APIStatus{ERROR, DONE}

class RestaurantsViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status



    //viewmodelben mindig private MutableLiveData
    //kivül a LiveDtata továbbítsa de nem lehet módosítani mert ő is csak megkapja
    private val _resInfo = MutableLiveData<List<Restaurant>>()
    val resInfo: LiveData<List<Restaurant>>
        get() = _resInfo

    private val _navigateSelectedRestaurant = MutableLiveData<Restaurant>()
    val navigateSelectedRestaurant: LiveData<Restaurant>
        get() = _navigateSelectedRestaurant


    private var job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)//milyen dispatcheren fusson le


    //a class constructora
    init {
        getRestaurantsProperties()
    }

    //Éttermek lekérése az APIból
    //status frissítése
    private fun getRestaurantsProperties() {
        viewModelScope.launch {
            //APIService -> meghivja a retrofitService.getPropertist
            val getInformation = RestaurantsApi.retrofitService.getProperties()

            try {

                // adigg kell vár amig meg nem kapja az adatokat
               // val listResult = getInformation.await()

                   val alma = async { getInformation.page }
                    _resInfo.value = getInformation.restaurants


                 // _status.value = "Success: ${listResult.restaurants.size}  restaurants are here"
            }catch (t: Exception){
                _status.value = "Failure: "+ t.message
               // Log.d("Hiba", "mdddddddsg" )
            }


        }
    }

    //amikor a háttérbe storul az app akkor leállítsa a corutint
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    //amikor ráklikkelünk egy restaurantra akkor átálitsa  a
    // _navigateToSelectedRestaurant MutableLivedata értékét a kiválasztot Restaurantra
    fun displayRestaurantDetails(restaurant: Restaurant){
        _navigateSelectedRestaurant.value = restaurant
    }

    //eltünteti ha már nem vagyok rajta
    fun displayRestaurantDetailsComplete(){
        _navigateSelectedRestaurant.value = null
    }
}

