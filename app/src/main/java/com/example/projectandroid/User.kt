package com.example.projectandroid

class User {

    var id : Int = 0
    var name : String = ""
    var age : Int = 0
    var location : String = ""
    var email : String = ""
    var telephone : String = ""

    constructor(name:String,age:Int, location:String, email:String, telephone:String){
        this.name = name
        this.age = age
        this.location = location
        this.email = email
        this.telephone = telephone

    }
}