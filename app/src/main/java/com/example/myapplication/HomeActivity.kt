package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.PopularRvItemsBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var db =Room.databaseBuilder(applicationContext,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("popular")){
                dataList.add(recipes[i]!!)
                }
                rvAdapter=PopularAdapter(dataList,this)
                binding.rvPopular.adapter=rvAdapter

        }
    }
}
