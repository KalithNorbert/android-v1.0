package com.example.projectandroid

import android.net.Uri
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
        val v =  inflater.inflate(R.layout.fragment_update, container, false)
        val btnBack = v.findViewById<Button>(R.id.btn_back)
        val btnUpdate = v.findViewById<Button>(R.id.btn_update)

        btnUpdate.setOnClickListener {
            //ha nem uress akkor beilleszt
            if (et1.text.toString().isNotEmpty() &&
                et2.text.toString().isNotEmpty() &&
                et3.text.toString().isNotEmpty() &&
                et4.text.toString().isNotEmpty() &&
                et5.text.toString().isNotEmpty()
            ){
                val db = context?.let { DataBaseHandler(context = it) }
                val data = db?.readData()
                var img = ""
                if (data != null)
                {
                    for (i in 0..(data.size -1)) {
                        img = data.get(i).image
                    }
                }
                db?.deleteData()//delete data
                val user = User(et1.text.toString(),et2.text.toString().toInt(),et3.text.toString(),et4.text.toString(),et5.text.toString(),img )
                db?.insertData(user)//insert user data's to database
                et1.text.clear()//editText urites
                et2.text.clear()
                et3.text.clear()
                et4.text.clear()
                et5.text.clear()
            }else{
                Toast.makeText(context,"Please complete all Data's ",Toast.LENGTH_SHORT).show()
            }

        }

        btnBack.setOnClickListener {
            //profile visszahivas
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