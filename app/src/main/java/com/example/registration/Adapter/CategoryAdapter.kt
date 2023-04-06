package com.example.registration.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Models.Category

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var context:Context
    lateinit var categories:List<Category>

    constructor(context: Context, category: List<Category>) : this() {
        this.context = context
        this.categories = category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    class CategoryViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)
    }

}