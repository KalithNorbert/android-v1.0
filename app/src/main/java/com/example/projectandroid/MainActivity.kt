package com.example.projectandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Splash) //loading splash screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //com.example.projectandroid.dataclass.Restaurant fragment betoltese
        img_butt.setOnClickListener{
            img_butt.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright))
            img_butt1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark))
            val firstFragment = Profile()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            //R.id.fragment - melyik fargmensbe
            //firstFragment - mit toltok be
            transaction.replace(R.id.nav_host_fragment,firstFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        //profile Fragment betoltese
        img_butt1.setOnClickListener {
            img_butt.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark))
            img_butt1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright))
            val secondFragment = RestaurantsFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment,secondFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }



}