package com.example.projectandroid.dataclass

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val address: String,
    val area: String,
    val city: String,
    val country: String,
    val id: Int,
    @Json(name = "image_url") val img_src: String,
    val lat: Double,
    val lng: Double,
    val mobile_reserve_url: String,
    val name: String,
    val phone: String,
    val postal_code: String,
    val price: Int,
    val reserve_url: String,
    val state: String
):Parcelable