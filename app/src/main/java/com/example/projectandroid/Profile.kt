package com.example.projectandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val loginButton = findViewById<Button>(R.id.btn_login)
        val registerButton = findViewById<Button>(R.id.btn_register)




        loginButton.setOnClickListener (View.OnClickListener
        {
            setContentView(R.layout.login)
        })

        registerButton.setOnClickListener (View.OnClickListener
        {
            setContentView(R.layout.registration)
        })


    }



    }

