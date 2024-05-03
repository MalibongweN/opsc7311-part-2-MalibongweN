package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activities : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var timesheetEntries: List<TimesheetEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        recyclerView = findViewById(R.id.recyclerViewTimesheet)
        emptyTextView = findViewById(R.id.recyclerViewTimesheet)

        if (timesheetEntries.isEmpty()) {
            emptyTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            emptyTextView.text = "No timesheet entries found. Create a new entry?"
            emptyTextView.setOnClickListener {
                createTimesheetEntry()
            }
        } else {
            emptyTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            val adapter = TimesheetEntryAdapter(timesheetEntries)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    class TimesheetEntryAdapter(timesheetEntries: List<TimesheetEntry>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }

    private fun createTimesheetEntry() {
        val intent = Intent(this, CreateSheet::class.java)
        startActivity(intent)
    }
}
