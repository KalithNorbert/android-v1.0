package com.example.projectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Update : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_update, container, false)

        val btnBack = v.findViewById<Button>(R.id.btn_back)
        val btnUpdate = v.findViewById<Button>(R.id.btn_update)





        btnBack.setOnClickListener {

            val profile = Profile()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(com.example.projectandroid.R.id.fragment,profile)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }


        return v
    }


}