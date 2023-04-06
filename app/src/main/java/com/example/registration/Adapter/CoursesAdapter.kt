package com.example.registration.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Models.Courses
import com.example.registration.R

class CoursesAdapter(): RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {

    private lateinit var context:Context
    private lateinit var courses:MutableList<Courses>

    constructor(context: Context, courses: MutableList<Courses>) : this() {
        this.context = context
        this.courses = courses
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var coursesItems:View = LayoutInflater.from(context).inflate(R.layout.courses_item, parent,false)
        return CoursesAdapter.CoursesViewHolder(coursesItems)
    }

    override fun getItemCount(): Int {
       return courses.size
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.coursesBg.setCardBackgroundColor(Color.parseColor(courses.get(position).getColor()))
        var imageId:Int = context.resources.getIdentifier(courses.get(position).getImg(),"drawable",context.packageName)
        holder.coursesImg.setImageResource(imageId)
        holder.coursesTitle.text = courses.get(position).getTitle()
        holder.coursesDate.text = courses.get(position).getDate()
        holder.coursesLevel.text = courses.get(position).getLevel()
    }

    class CoursesViewHolder : RecyclerView.ViewHolder {
        lateinit var coursesBg: CardView
        lateinit var coursesImg: ImageView
        lateinit var coursesTitle: TextView
        lateinit var coursesDate: TextView
        lateinit var coursesLevel: TextView
        constructor(itemView: View):super(itemView){
            coursesBg = itemView.findViewById(R.id.coursesBg)
            coursesImg = itemView.findViewById(R.id.txtImg)
            coursesTitle = itemView.findViewById(R.id.txtTitle)
            coursesDate = itemView.findViewById(R.id.coursesDate)
            coursesLevel = itemView.findViewById(R.id.coursesLevel)
        }

    }
}