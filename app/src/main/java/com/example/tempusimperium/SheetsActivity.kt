package com.example.tempusimperium

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SheetsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryDisplay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheets)

        recyclerView = findViewById(R.id.showSheets)
        categoryDisplay = findViewById(R.id.categoryDisplay)

        val category = intent.getStringExtra("category")
        categoryDisplay.text = category

        GlobalScope.launch(Dispatchers.Main) {
            val timesheetEntries = fetchTimesheetEntriesFromDatabase(category ?: "")
            setupRecyclerView(timesheetEntries)
        }
    }

    private fun fetchTimesheetEntriesFromDatabase(s: String): List<TimesheetEntry> {
        TODO("Not yet implemented")
    }


    private fun setupRecyclerView(timesheetEntries: List<TimesheetEntry>) {

    }
}

