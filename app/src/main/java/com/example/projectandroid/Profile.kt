package com.example.projectandroid

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*


class Profile : AppCompatActivity() {

    lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val loginButton = findViewById<Button>(R.id.btn_login)
        val registerButton = findViewById<Button>(R.id.btn_register)



        handler = DatabaseHelper(this)









        //login screen load
        loginButton.setOnClickListener (View.OnClickListener
        {
            setContentView(R.layout.login)

        })

        //register screen load
        registerButton.setOnClickListener (View.OnClickListener
        {
            setContentView(R.layout.registration)
        })



        bt_save.setOnClickListener {
            handler.insertUserData(et_username.text.toString(),et_email.text.toString(),et_password.text.toString())
        }


        l_login.setOnClickListener {
            if(handler.userPresent(l_email.text.toString(),l_password.text.toString()))
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"e-mail or password is incorrect",Toast.LENGTH_SHORT).show()
        }



    }


}

