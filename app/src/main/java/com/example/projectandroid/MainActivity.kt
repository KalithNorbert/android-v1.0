package com.example.projectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btProfile = findViewById<ImageButton>(R.id.img_butt1)




        btProfile.setOnClickListener{
            startProfile()
        }


    }

    private fun startProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)

    }

}