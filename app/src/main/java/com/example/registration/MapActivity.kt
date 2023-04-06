package com.example.registration

import android.media.MediaRouter.RouteCategory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Adapter.CategoryAdapter
import com.example.registration.Adapter.CoursesAdapter
import com.example.registration.Models.Category
import com.example.registration.Models.Courses
import com.example.registration.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var coursesAdapter: CoursesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var  categoryList:MutableList<Category> = mutableListOf()
        categoryList.add(Category(1,"Игры"))
        categoryList.add(Category(2,"Сайты"))
        categoryList.add(Category(3,"Языки"))
        categoryList.add(Category(4,"Прочее"))

        setCategoryRecycler(categoryList)

        var  coursesList:MutableList<Courses> = mutableListOf()
        coursesList.add(Courses(1,"java","Профессия Java\nразработчик","1 января","начальный","#424345"))
        coursesList.add(Courses(2,"python","Профессия Python\nразработчик","10 января","начальный","#9fa52d"))

        setCoursesRecycler(coursesList)


    }

    private fun setCoursesRecycler(coursesList: MutableList<Courses>) {
        var layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.coursesRecycler.layoutManager = layoutManager

        coursesAdapter = CoursesAdapter(this,coursesList)
        binding.coursesRecycler.adapter = coursesAdapter
    }

    private fun setCategoryRecycler(categoryList: MutableList<Category>) {
        var layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.categoryRecycle.layoutManager = layoutManager

        categoryAdapter = CategoryAdapter(this,categoryList)
        binding.categoryRecycle.adapter = categoryAdapter

    }
}