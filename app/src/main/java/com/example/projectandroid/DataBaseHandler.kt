package com.example.projectandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_LOCATION = "location"
val COL_EMAIL = "email"
val COL_TELEPHONE = "age"
val COL_ID = "id"


class DataBaseHandler(val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,null, 1){

    private var db: SQLiteDatabase? = null
    override fun onCreate(db: SQLiteDatabase) {
        this.db = db
        val createTable = ("CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_AGE + " INTEGER," +
                COL_LOCATION + " TEXT," +
                COL_EMAIL + " TEXT," +
                COL_TELEPHONE + " TEXT" + ")")

        db.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(user : User){
        val cv = ContentValues()
        cv.put(COL_NAME,user.name)
        cv.put(COL_AGE,user.age)
        cv.put(COL_LOCATION,user.location)
        cv.put(COL_EMAIL,user.email)
        cv.put(COL_TELEPHONE,user.telephone)



        val  result =  db?.insert(TABLE_NAME, null,cv)

        Toast.makeText(context,"Ide is",Toast.LENGTH_SHORT).show()
        if(result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

    }

}