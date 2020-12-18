package com.example.projectandroid.datas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.databinding.RestaurantDataFragmentBinding

class RestaurantDataFragment : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = RestaurantDataFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val restaurantDetails = RestaurantDataFragmentArgs.fromBundle(arguments!!).selectedRestaurant
        val viewModelFactory = RestaurantDataViewModelFactory(restaurantDetails, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(RestaurantDataViewMode::class.java)


        binding.btnGoogleMaps.setOnClickListener() {
            val latitude = restaurantDetails.lat
            val longitude = restaurantDetails.lng
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        binding.btnTelephone.setOnClickListener(){
            val number = restaurantDetails.phone
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            phoneIntent.setData(Uri.parse("tel:$number"))
            startActivity(phoneIntent)
        }


        return binding.root
    }



}