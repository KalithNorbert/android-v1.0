package com.example.projectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) //loading screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Restaurant fragment betoltese
        img_butt.setOnClickListener{
            img_butt.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright))
            img_butt1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark))
            val firstFragment = Profile()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            //R.id.fragment - melyik fargmensbe
            //firstFragment - mit toltok be
            transaction.replace(R.id.fragment,firstFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        //profile Fragment betoltese
        img_butt1.setOnClickListener {
            img_butt.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark))
            img_butt1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright))
            val secondFragment = Restaurants()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment,secondFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }



}