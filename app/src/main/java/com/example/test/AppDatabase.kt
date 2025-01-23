package com.example.test

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Database(entities = [Number::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, dbPath: String): AppDatabase {
            Log.d("XEMNE", "getDatabase called with dbPath: $dbPath")

            return INSTANCE ?: synchronized(this) {
                Log.d("XEMNE", "Instance is null, creating new database instance")

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    dbPath
                ).createFromFile(File(dbPath)).build()

                Log.d("XEMNE", "Database instance created")

                INSTANCE = instance
                instance
            }.also {
                Log.d("XEMNE", "Returning database instance")
            }
        }
    }
}