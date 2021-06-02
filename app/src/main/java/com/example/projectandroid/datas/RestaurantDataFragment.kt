package com.example.projectandroid.datas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.projectandroid.DataBaseHandler
import com.example.projectandroid.R
import com.example.projectandroid.User
import com.example.projectandroid.databinding.RestaurantDataFragmentBinding
import com.example.projectandroid.favorite.FavoriteDataBase
import com.example.projectandroid.favorite.RestaurantFavorite
import kotlinx.android.synthetic.main.fragment_change.*
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

        //kiveszi a bundleből a választot adatot
        val restaurantDetails = RestaurantDataFragmentArgs.fromBundle(arguments!!).selectedRestaurant

        //visszajelzést kap, hogy létezike a lekért objektum
        val viewModelFactory = RestaurantDataViewModelFactory(restaurantDetails, application)

        //melyik viewModel kell nekem
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantDataViewMode::class.java)


        val db = context?.let { FavoriteDataBase(context = it) }

        if (db?.favoriteCheckedId(restaurantDetails.id) == true){
            binding.btnFavorite.text = "Remove to Favorite"

        }else{
            binding.btnFavorite.text = "Add to favorite"
        }





        //Add and remove to favorite restaurants
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

               //reload fragment to see changes
                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false)
                }
                ft.detach(this).attach(this).commit()
            }

            if (binding.btnFavorite.text == "Remove to Favorite")
            {
                val db = context?.let { FavoriteDataBase(context = it) }
                db!!.deleteDataFavorite(restaurantDetails.id)
                btn_favorite.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark))

                //reload fragment
                val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false)
                }
                ft.detach(this).attach(this).commit()

            }

        }


        ////picturechange
        binding.btnPic.setOnClickListener(){
            if (binding.btnFavorite.text == "Add to favorite")
            {
                Toast.makeText(context, "First add to favorite to change Pic", Toast.LENGTH_SHORT).show()
            }
            else
            {
                binding.btnPic.text = "ADD"
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent,456)
            }
        }

        if ( binding.btnFavorite.text == "Remove to Favorite" &&  binding.btnPic.text == "ADD")
        {
            val db = context?.let { FavoriteDataBase(context = it) }

            val data = db?.readDataFavorite()

            val ida = db!!.favoriteId(restaurantDetails.id)
            val img = Uri.parse(data!!.get(ida).img_src)
            main_photo_image.setImageURI(img)




            //reload fragment
            val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageV = requireView().findViewById<ImageView>(R.id.main_photo_image)
        val restaurantDetails =
            RestaurantDataFragmentArgs.fromBundle(requireArguments()).selectedRestaurant

        if (requestCode == 456) {
            imageV!!.setImageURI(data?.data)
            val datapicture = data?.data.toString()

            val db = context?.let { FavoriteDataBase(context = it) }
            db!!.updateFavorites(restaurantDetails.id, datapicture)
        }
    }
}