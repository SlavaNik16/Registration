package com.example.registration.Models

import android.provider.ContactsContract.CommonDataKinds.Email

public class User {
    private lateinit var Name: String
    private lateinit var Phone: String
    private lateinit var Email: String
    private lateinit var Password: String


    constructor(){

    }

    constructor(name:String, email:String, pass:String, phone: String){
        this.Name = name
        this.Phone = phone
        this.Email = email
        this.Password = pass

    }

    fun getName(): String = Name
    fun setName(name:String){
        this.Name = name
    }
    fun getEmail(): String = Email
    fun setEmail(email:String){
        this.Email = email
    }
    fun getPassword(): String =  Password
    fun setPassword(password:String){
        this.Password = password
    }
    fun getPhone(): String = Phone
    fun setPhone(phone:String){
        this.Phone = phone
    }
}