package com.example.projectandroid.favorite

class RestaurantFavorite {
    val dbid: Int = 0
    var address: String = ""
    var area: String = ""
    var city: String = ""
    var country: String = ""
    var id: Int = 0
    var img_src: String = ""
    var lat: Double = 0.0
    var lng: Double = 0.0
    var mobileReserveUrl: String = ""
    var name: String = ""
    var phone: String = ""
    var postalCode: String = ""
    var price: Int = 0
    var reserveUrl: String = ""
    var state: String = ""
    var check: Int = 0

    constructor(address: String,area: String, city: String,country: String, id: Int,img_src: String,lat: Double,lng: Double,
                mobileReserveUrl: String,name: String,phone: String,postalCode: String,price: Int,reserveUrl: String,state: String ,check:Int = 0 ){
        this.address = address
        this.area = area
        this.city = city
        this.country = country
        this.id = id
        this.img_src = img_src
        this.lat = lat
        this.lng = lng
        this.mobileReserveUrl = mobileReserveUrl
        this.name = name
        this.phone = phone
        this.postalCode = postalCode
        this.price = price
        this.reserveUrl = reserveUrl
        this.state = state
        this.check = check

    }
    constructor(){ }
}