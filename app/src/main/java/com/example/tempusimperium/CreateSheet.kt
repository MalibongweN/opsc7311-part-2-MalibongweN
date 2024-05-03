package com.example.tempusimperium

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.ArrayAdapter

class CreateSheet : AppCompatActivity() {

    private lateinit var buttonPickDate: Button
    private lateinit var buttonPickStartTime: Button
    private lateinit var buttonPickEndTime: Button
    private lateinit var buttonSave: Button
    private lateinit var spinnerCategory: Spinner
    private lateinit var txtDescription: EditText

    private var selectedDate = Calendar.getInstance()
    private var selectedStartTime = Calendar.getInstance()
    private var selectedEndTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sheet)

        buttonPickDate = findViewById(R.id.buttonPickDate)
        buttonPickStartTime = findViewById(R.id.buttonPickStartTime)
        buttonPickEndTime = findViewById(R.id.buttonPickEndTime)
        buttonSave = findViewById(R.id.buttonSave)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        txtDescription = findViewById(R.id.editTextDescription)

        //action listeners
        buttonPickDate.setOnClickListener {
            showDatePickerDialog()
        }

        buttonPickStartTime.setOnClickListener {
            showStartTimePickerDialog()
        }

        buttonPickEndTime.setOnClickListener {
            showEndTimePickerDialog()
        }

        buttonSave.setOnClickListener {
            val description = txtDescription.text.toString()
            val selectedCategory = spinnerCategory.selectedItem.toString()
            saveTimesheetEntry(description, selectedCategory,)
        }

        val categoriesList = listOf("Category 1", "Category 2", "Category 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoriesList)
        spinnerCategory.adapter = adapter
    }

    private fun saveTimesheetEntry(description: String, selectedCategory: String) {
        uploadEntry(description, selectedCategory)
    }

    private fun uploadEntry(description: String, selectedCategory: String) {
        TODO("Not yet implemented")
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                buttonPickDate.text = dateFormat.format(selectedDate.time)
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showStartTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedStartTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedStartTime.set(Calendar.MINUTE, minute)
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                buttonPickStartTime.text = timeFormat.format(selectedStartTime.time)
            },
            selectedStartTime.get(Calendar.HOUR_OF_DAY),
            selectedStartTime.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun showEndTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedEndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedEndTime.set(Calendar.MINUTE, minute)
                val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                buttonPickEndTime.text = timeFormat.format(selectedEndTime.time)
            },
            selectedEndTime.get(Calendar.HOUR_OF_DAY),
            selectedEndTime.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun saveTimesheetEntry(description: String, selectedCategory: String, imageURL: String) {
        val timesheetEntry = TimesheetEntry(
            selectedDate.timeInMillis,
            selectedStartTime.timeInMillis,
            selectedEndTime.timeInMillis,
            description,
            selectedCategory,
            imageURL
        )

        // Assuming you have a FirebaseHelper class with a saveTimesheetEntry function
        FirebaseHelper.saveTimesheetEntry(timesheetEntry)

        Toast.makeText(this, "Timesheet entry saved successfully", Toast.LENGTH_SHORT).show()
    }
}

data class TimesheetEntry(
    val date: Long,
    val startTime: Long,
    val endTime: Long,
    val description: String,
    val category: String,
    val imageURL: String
)

object FirebaseHelper {
    private val db = FirebaseFirestore.getInstance()
    private val timesheetCollection = db.collection("timesheets")

    fun saveTimesheetEntry(timesheetEntry: TimesheetEntry) {
        timesheetCollection.add(timesheetEntry)
            .addOnSuccessListener { documentReference ->
                println("Timesheet entry saved with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error saving timesheet entry: $e")
            }
    }
}