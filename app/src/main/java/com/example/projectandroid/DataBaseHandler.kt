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
val COL_TELEPHONE = "telephone"
val COL_IMAGE = "image"
val COL_ID = "id"


class DataBaseHandler(val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        //letrehozashoz szukseges sql parancs
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE + " INTEGER," +
                COL_EMAIL + " VARCHAR(256)," +
                COL_TELEPHONE + " VARCHAR(256)," +
                COL_LOCATION + " VARCHAR(256)," +
                COL_IMAGE + " VARCHAR(256))"

        db?.execSQL(createTable)

    }

    //uj verzio eseten
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       //
    }
    fun insertData(user : User){
        //ket fele database objektum van : writableDatabase, readableDatabase
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME,user.name)
        cv.put(COL_AGE,user.age)
        cv.put(COL_EMAIL,user.email)
        cv.put(COL_TELEPHONE,user.telephone)
        cv.put(COL_LOCATION,user.location)
        cv.put(COL_IMAGE,user.image)
        //beszurás az adatbázisba
        val  result =  db?.insert(TABLE_NAME, null,cv)
        if(result == (-1).toLong())//ha rowID -1 akkro hiba tortent
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    //adatok kiolvasása és visszatérítése
    fun readData() : MutableList<User>{
        val list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.age = result.getString(result.getColumnIndex(COL_AGE)).toInt()
                user.email  = result.getString(result.getColumnIndex(COL_EMAIL))
                user.telephone = result.getString(result.getColumnIndex(COL_TELEPHONE))
                user.location = result.getString(result.getColumnIndex(COL_LOCATION))
                user.image = result.getString(result.getColumnIndex(COL_IMAGE))
                list.add(user)
            }while (result.moveToNext())
            }

        result.close()
        db.close()
        return list
    }

    //tábla üritése
    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }

}