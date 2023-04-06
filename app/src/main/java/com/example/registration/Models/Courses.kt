package com.example.registration.Models

class Courses {
    private var id:Int = 0
    private var img:String = ""
    private var title:String = ""
    private var date:String = ""
    private var level:String = ""
    private var color:String = ""

    constructor(id: Int, img: String, title: String, date: String, level: String, color: String) {
        this.id = id
        this.img = img
        this.title = title
        this.date = date
        this.level = level
        this.color = color
    }

    fun getId()=id
    fun getImg()=img
    fun getTitle() = title
    fun getDate() = date
    fun getLevel() = level
    fun getColor() = color

    fun setId(id:Int){
        this.id = id
    }
    fun setImg(img: String){
        this.img = img
    }
    fun setTitle(title: String){
        this.title = title
    }
    fun setDate(date: String){
        this.date = date
    }
    fun setLevel(level: String){
        this.level = level
    }
    fun setColor(color: String){
        this.color = color
    }

}