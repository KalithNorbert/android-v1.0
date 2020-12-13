package com.example.projectandroid.dataclass

import com.example.projectandroid.dataclass.Restaurant

data class RestaurantsClass(
    val page: Int,
    val per_page: String,
    val restaurants: List<Restaurant>,
    val total_entries: Int
)