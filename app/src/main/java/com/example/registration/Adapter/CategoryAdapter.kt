package com.example.registration.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Models.Category
import com.example.registration.R

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var context:Context
    lateinit var categories:MutableList<Category>

    constructor(context: Context, category: MutableList<Category>) : this() {
        this.context = context
        this.categories = category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var categoryItems:View = LayoutInflater.from(context).inflate(R.layout.category_item, parent,false)
        return CategoryViewHolder(categoryItems)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
         holder.categoryTitle.text = categories.get(position).getTitle()
    }


    class CategoryViewHolder : RecyclerView.ViewHolder {

        lateinit var categoryTitle:TextView
        constructor(itemView: View) : super(itemView){
            categoryTitle = itemView.findViewById(R.id.categoryTitle)
        }
    }

}