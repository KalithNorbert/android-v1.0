package com.example.projectandroid

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_change.*
import kotlinx.android.synthetic.main.fragment_profile.*


class Profile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnUpdate = v.findViewById<Button>(R.id.btn_update_frag)
        val btnChange = v.findViewById<Button>(R.id.btn_change)

        val imageV = v.findViewById<ImageView>(R.id.img_view_profile)

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

                val img = Uri.parse(data.get(i).image)
                imageV!!.setImageURI(img)
            }

        }

        btnChange.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,456)
        }

        if (tv11.text == "Name:")
        {
            Toast.makeText(context,"Please insert data first",Toast.LENGTH_SHORT).show()
        }




        btnUpdate.setOnClickListener {
            val UpdateFragment1 = Update()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(R.id.nav_host_fragment,UpdateFragment1)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

/*
        btnChange.setOnClickListener {
            val UpdateFragment = Change()
            val manager = fragmentManager
            val transaction = manager?.beginTransaction()
            transaction?.replace(R.id.fragment,UpdateFragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }*/

        return v

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageV = requireView().findViewById<ImageView>(R.id.img_view_profile)

        if(requestCode == 456){
            imageV!!.setImageURI(data?.data)
            val datapicture = data?.data.toString()

            val db = context?.let { DataBaseHandler(context = it) }
            var data1 = db?.readData()

            if(id>1){
                db!!.deleteData()
            }

            if (data1 != null)
            {
                for (i in 0..(data1.size -1)) {
                    var id = data1.get(i).id
                    var name = data1.get(i).name
                    var age = data1.get(i).age
                    var email = data1.get(i).email
                    var telephone = data1.get(i).telephone
                    var location = data1.get(i).location
                    var user = User(name,age,email,telephone,location,datapicture)

                   db?.insertData(user)
                }
            }

            Log.d("akarmi",data?.data.toString())

        }else if (requestCode == 123){
            img_view_change.setImageURI(data?.data)
        }
    }

}


