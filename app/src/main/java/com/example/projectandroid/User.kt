package com.example.projectandroid

class User {

    // a USER objektum strukturajanak meghatarozasara

    var id : Int = 0
    var name : String = ""
    var age : Int = 0
    var location : String = ""
    var email : String = ""
    var telephone : String = ""
    var image : String = ""

    constructor(name:String,age:Int, location:String, email:String, telephone:String, image:String){
        this.name = name
        this.age = age
        this.location = location
        this.email = email
        this.telephone = telephone
        this.image = image
    }
    constructor(){

    }
}