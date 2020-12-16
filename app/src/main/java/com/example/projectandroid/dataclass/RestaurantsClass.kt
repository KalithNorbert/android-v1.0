package com.example.projectandroid.dataclass

import android.os.Parcelable
import com.example.projectandroid.dataclass.Restaurant
import kotlinx.android.parcel.Parcelize


data class RestaurantsClass(
    val page: Int,
    val per_page: String,
    val restaurants: List<Restaurant>,
    val total_entries: Int
)