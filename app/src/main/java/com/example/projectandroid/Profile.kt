package com.example.projectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val vv = inflater.inflate(R.layout.activity_main, container, false)

        val btn_update = v.findViewById<Button>(R.id.btn_update_frag)


        btn_update.setOnClickListener {

            val UpdateFragment = Update()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(com.example.projectandroid.R.id.fragment,UpdateFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return v

    }

}


