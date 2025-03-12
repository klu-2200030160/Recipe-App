package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "tittle") // Match the database column name
    val title: String,

    @ColumnInfo(name = "img")
    val img: String,

    @ColumnInfo(name = "des")
    val des: String,

    @ColumnInfo(name = "ing")
    val ing: String,

    @ColumnInfo(name = "category")
    val category: String
)