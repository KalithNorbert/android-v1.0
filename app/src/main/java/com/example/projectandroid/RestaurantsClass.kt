package com.example.projectandroid

import com.example.projectandroid.Restaurant

data class RestaurantsClass(
    val page: Int,
    val per_page: String,
    val restaurants: List<Restaurant>,
    val total_entries: Int
)