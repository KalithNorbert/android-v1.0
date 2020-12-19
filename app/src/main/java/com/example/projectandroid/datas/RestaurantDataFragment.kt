package com.example.projectandroid.datas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.DataBaseHandler
import com.example.projectandroid.databinding.RestaurantDataFragmentBinding
import com.example.projectandroid.favorite.FavoriteDataBase
import com.example.projectandroid.favorite.RestaurantFavorite
import kotlinx.android.synthetic.main.restaurant_data_fragment.*

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





        val db = context?.let { FavoriteDataBase(context = it) }

        if (db?.favoriteCheckedId(restaurantDetails.id) == true){
            binding.btnFavorite.text = "Remove to Favorite"
        }else{
            binding.btnFavorite.text = "Add to favorite"
        }



        binding.btnFavorite.setOnClickListener(){
            if (binding.btnFavorite.text == "Add to favorite")
            {

                val newFavorite = RestaurantFavorite(
                    restaurantDetails.address,
                    restaurantDetails.area,
                    restaurantDetails.city,
                    restaurantDetails.country,
                    restaurantDetails.id,
                    restaurantDetails.img_src,
                    restaurantDetails.lat,
                    restaurantDetails.lng,
                    restaurantDetails.mobile_reserve_url,
                    restaurantDetails.name,
                    restaurantDetails.phone,
                    restaurantDetails.postal_code,
                    restaurantDetails.price,
                    restaurantDetails.reserve_url,
                    restaurantDetails.state
                    )

                db?.insertDataFavorite(newFavorite)
                btn_favorite.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright))
            }

            if (binding.btnFavorite.text == "Remove to Favorite")
            {
                val db = context?.let { FavoriteDataBase(context = it) }
                db!!.deleteDataFavorite(restaurantDetails.id)
                btn_favorite.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark))
            }

        }


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