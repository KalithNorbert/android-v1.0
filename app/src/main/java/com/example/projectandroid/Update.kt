package com.example.projectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_update.*

class Update : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_update, container, false)

        val btnBack = v.findViewById<Button>(R.id.btn_back)
        val btnUpdate = v.findViewById<Button>(R.id.btn_update)



        btnUpdate.setOnClickListener {
            if (et1.text.toString().isNotEmpty() &&
                et2.text.toString().isNotEmpty() &&
                et3.text.toString().isNotEmpty() &&
                et4.text.toString().isNotEmpty() &&
                et5.text.toString().isNotEmpty()
            ){

                val user = User(et1.text.toString(),et2.text.toString().toInt(),et3.text.toString(),et4.text.toString(),et5.text.toString() )
                val db = context?.let { DataBaseHandler(context = it) }
                    db?.insertData(user)


            }else{
                Toast.makeText(context,"Please complete all Data's ",Toast.LENGTH_SHORT).show()
            }

        }



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