package com.example.projectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        val btn_update = v.findViewById<Button>(R.id.btn_update_frag)
        val btn_change = v.findViewById<Button>(R.id.btn_change)
        val tv11 = v.findViewById<TextView>(R.id.tv1)
        val tv12 = v.findViewById<TextView>(R.id.tv2)
        val tv13 = v.findViewById<TextView>(R.id.tv3)
        val tv14 = v.findViewById<TextView>(R.id.tv4)
        val tv15 = v.findViewById<TextView>(R.id.tv5)


        val db = context?.let { DataBaseHandler(context = it) }

        val data = db?.readData()


        if (data != null)
        {
            for (i in 0..(data.size -1)) {
                tv11.text = "Name: "+ data.get(i).name
                tv12.text = "Age: "+ data.get(i).age
                tv13.text = "Number: "+ data.get(i).email
                tv14.text = "Address: "+ data.get(i).telephone
                tv15.text = "E-mail: "+ data.get(i).location
            }

        }

        if (tv11.text == "Name:")
        {
            Toast.makeText(context,"Please insert data first",Toast.LENGTH_SHORT).show()
        }




        btn_update.setOnClickListener {
            val UpdateFragment1 = Update()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(R.id.fragment,UpdateFragment1)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }


        btn_change.setOnClickListener {
            val UpdateFragment = Change()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(R.id.fragment,UpdateFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return v

    }

}


