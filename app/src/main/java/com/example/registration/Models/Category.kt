package com.example.registration.Models

class Category {
    var id: Int = 0
    var title: String = ""

    constructor(id: Int, title: String) {
        this.id = id
        this.title = title
    }

    fun getId()=id
    fun getTitle() = title

    fun setId(id:Int){
        this.id = id
    }
    fun setTitle(title: String){
        this.title = title
    }
}
