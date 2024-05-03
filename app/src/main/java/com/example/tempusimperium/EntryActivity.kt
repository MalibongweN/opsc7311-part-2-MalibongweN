package com.example.tempusimperium

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class EntryActivity : AppCompatActivity() {

    private lateinit var editTextCategory: EditText
    private lateinit var editTextDate: EditText
    private lateinit var startDay: EditText
    private lateinit var editTextDate3: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var buttonAddPhoto: Button
    private lateinit var buttonCreateEntry: Button
    private lateinit var userInputPhoto: ImageView

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null

    private val numberFormat = NumberFormat.getInstance()
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        editTextCategory = findViewById(R.id.editTextCategory)
        editTextDate = findViewById(R.id.editTextDate)
        startDay = findViewById(R.id.startDay)
        editTextDate3 = findViewById(R.id.editTextDate3)
        editTextDescription = findViewById(R.id.editTextDescription)
        buttonAddPhoto = findViewById(R.id.buttonAddPhoto)
        buttonCreateEntry = findViewById(R.id.buttonCreateEntry)
        userInputPhoto = findViewById(R.id.userInputPhoto)

        buttonAddPhoto.setOnClickListener {
            launchGallery()
        }

        buttonCreateEntry.setOnClickListener {
            uploadEntry()
        }
    }

    private fun launchGallery() {
        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(galleryIntent, "Select Picture"),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            userInputPhoto.setImageURI(filePath)
        }
    }

    private fun uploadEntry() {
        val category = editTextCategory.text.toString()
        val date = formatDate(editTextDate.text.toString())
        val startDate = formatDate(startDay.text.toString())
        val endDate = formatDate(editTextDate3.text.toString())
        val description = editTextDescription.text.toString()

        if (category.isNotEmpty() && date.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty() && description.isNotEmpty() && filePath != null) {
            val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}")
            storageRef.putFile(filePath!!)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                        val imageURL = uri.toString()
                        val timesheetEntry = TimesheetEntry(category, date, startDate, endDate, description, imageURL)
                        saveTimesheetEntry(timesheetEntry)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to upload image: $e", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun TimesheetEntry(
        date: String,
        startTime: String,
        endTime: String,
        description: String,
        category: String,
        imageURL: String
    ): TimesheetEntry {
        TODO("Not yet implemented")
    }

    private fun saveTimesheetEntry(timesheetEntry: TimesheetEntry) {
        val db = FirebaseFirestore.getInstance()
        db.collection("timesheets")
            .add(timesheetEntry)
            .addOnSuccessListener {
                Toast.makeText(this, "Entry added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding entry: $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun formatDate(dateString: String): String {
        val date = dateFormat.parse(dateString)
        return dateFormat.format(date)
    }
}
