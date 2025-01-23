package com.example.test

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbPath = """D:\AndroidStudioProjects\language_infinite_app2.db"""
        db = AppDatabase.getDatabase(this, dbPath)

        CoroutineScope(Dispatchers.IO).launch {
            val numbers = db.numberDao().getAll()
            Log.d("XEMNE", "size: ${numbers.size}")
            numbers.forEach {
                Log.d("XEMNE", "ID: ${it.id}, jpIpa: ${it.jpIpa}")
            }
        }

    }
}