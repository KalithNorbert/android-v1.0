package com.example.projectandroid.datas

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.R
import com.example.projectandroid.RestaurantsViewModel
import com.example.projectandroid.databinding.RestaurantDataFragmentBinding
import com.example.projectandroid.dataclass.Restaurant

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

        return binding.root
    }



}