package com.example.registration.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Models.Courses

class CoursesAdapter(): RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {

    private lateinit var context:Context
    private lateinit var courses:MutableList<Courses>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
       return courses.size
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class CoursesViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View):super(itemView){

        }

    }
}