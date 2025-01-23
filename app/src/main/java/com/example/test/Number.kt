package com.example.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NumberTable")
data class Number(
    @PrimaryKey val id: Int,
    val jpIpa: String
)