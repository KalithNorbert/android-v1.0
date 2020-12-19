package com.example.projectandroid.favorite


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.projectandroid.*


val DATABASE_NAME = "FavoriteDb"
val TABLE_NAME = "Favorite"
val COL_DBID = "dbid"
val COL_ADDRESS = "address"
val COL_AREA = "area"
val COL_CITY = "city"
val COL_COUNTRY = "country"
val COL_ID = "id"
val COL_IMAGE = "img_src"
val COL_LAT = "lat"
val COL_LNG = "lng"
val COL_MOBILE_RESERVE_URL = "mobile_reserve_url"
val COL_NAME = "name"
val COL_PHONE = "phone"
val COL_POSTAL_CODE = "postal_code"
val COL_PRICE = "price"
val COL_RESERVE_URL = "reserve_url"
val COL_STATE = "state"
val COL_CHECKED = "check"


class FavoriteDataBase(val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        //letrehozashoz szukseges sql parancs
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_DBID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ID +" INTEGER ," +
                COL_ADDRESS + " VARCHAR(256)," +
                COL_AREA + " VARCHAR(256)," +
                COL_CITY + " VARCHAR(256)," +
                COL_COUNTRY + " VARCHAR(256)," +
                COL_IMAGE + " VARCHAR(256)," +
                COL_LAT + " REAL," +
                COL_LNG + " REAL," +
                COL_MOBILE_RESERVE_URL + " VARCHAR(256)," +
                COL_NAME + " VARCHAR(256)," +
                COL_PHONE + " VARCHAR(256)," +
                COL_POSTAL_CODE + " VARCHAR(256)," +
                COL_PRICE + " INTEGER," +
                COL_RESERVE_URL + " VARCHAR(256)," +
                COL_STATE + " VARCHAR(256)," +
                COL_STATE + " INTEGER)"

        db?.execSQL(createTable)

    }

    //uj verzio eseten
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //
    }

    fun insertDataFavorite(restaurantfavorite: RestaurantFavorite){
        //ket fele database objektum van : writableDatabase, readableDatabase
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ID, restaurantfavorite.id)
        cv.put(COL_ADDRESS, restaurantfavorite.address)
        cv.put(COL_AREA, restaurantfavorite.area)
        cv.put(COL_CITY, restaurantfavorite.city)
        cv.put(COL_COUNTRY, restaurantfavorite.country)
        cv.put(COL_IMAGE, restaurantfavorite.imageUrl)
        cv.put(COL_LAT, restaurantfavorite.lat)
        cv.put(COL_LNG, restaurantfavorite.lng)
        cv.put(COL_MOBILE_RESERVE_URL, restaurantfavorite.mobileReserveUrl)
        cv.put(COL_NAME, restaurantfavorite.name)
        cv.put(COL_PHONE, restaurantfavorite.phone)
        cv.put(COL_POSTAL_CODE, restaurantfavorite.postalCode)
        cv.put(COL_PRICE, restaurantfavorite.price)
        cv.put(COL_RESERVE_URL, restaurantfavorite.reserveUrl)
        cv.put(COL_STATE, restaurantfavorite.state)
        //beszurás az adatbázisba
        val  result =  db?.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong())//ha rowID -1 akkro hiba tortent
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    //adatok kiolvasása és visszatérítése
    fun readDataFavorite() : MutableList<RestaurantFavorite>{
        val list : MutableList<RestaurantFavorite> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                val restaurantfavorite = RestaurantFavorite()
                restaurantfavorite.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                restaurantfavorite.address = result.getString(result.getColumnIndex(COL_ADDRESS))
                restaurantfavorite.area = result.getString(result.getColumnIndex(COL_AREA))
                restaurantfavorite.city  = result.getString(result.getColumnIndex(COL_CITY))
                restaurantfavorite.country = result.getString(result.getColumnIndex(COL_COUNTRY))
                restaurantfavorite.imageUrl = result.getString(result.getColumnIndex(COL_IMAGE))
                restaurantfavorite.lat = result.getString(result.getColumnIndex(COL_LAT)).toDouble()
                restaurantfavorite.lng = result.getString(result.getColumnIndex(COL_LNG)).toDouble()
                restaurantfavorite.mobileReserveUrl = result.getString(result.getColumnIndex(COL_MOBILE_RESERVE_URL))
                restaurantfavorite.name = result.getString(result.getColumnIndex(COL_NAME))
                restaurantfavorite.phone = result.getString(result.getColumnIndex(COL_PHONE))
                restaurantfavorite.postalCode = result.getString(result.getColumnIndex(COL_POSTAL_CODE))
                restaurantfavorite.price = result.getString(result.getColumnIndex(COL_PRICE)).toInt()
                restaurantfavorite.reserveUrl = result.getString(result.getColumnIndex(COL_RESERVE_URL))
                restaurantfavorite.state = result.getString(result.getColumnIndex(COL_STATE))
                list.add(restaurantfavorite)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    //tábla üritése
    fun deleteDataFavorite(num: Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COL_ID + "=?", arrayOf(num.toString()))
        db.close()
    }


    fun favoriteCheckedId(num: Int): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                if (num == result.getString(result.getColumnIndex(com.example.projectandroid.favorite.COL_ID)).toInt()){
                    return true
                }
            }while (result.moveToNext())
        }
        return false
    }

}