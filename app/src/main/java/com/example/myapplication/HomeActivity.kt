package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "recipe.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject: RecipeDao = db.recipeDao()
        val recipes = daoObject.getAll()

        for (recipe in recipes) {
            if (recipe.category.contains("popular")) {
                dataList.add(recipe)
            }
        }

        rvAdapter = PopularAdapter(dataList, this)
        binding.rvPopular.adapter = rvAdapter
    }
}
