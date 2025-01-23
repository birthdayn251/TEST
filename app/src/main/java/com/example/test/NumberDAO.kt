package com.example.test

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NumberDAO {
    @Query("SELECT * FROM NumberTable")
    fun getAll(): List<Number>
}

