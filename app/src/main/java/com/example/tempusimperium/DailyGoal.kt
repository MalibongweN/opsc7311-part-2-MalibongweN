package com.example.tempusimperium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DailyGoal : AppCompatActivity() {

    private lateinit var editTextMinGoal: EditText
    private lateinit var editTextMaxGoal: EditText
    private lateinit var editTextDate: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_goal)

        editTextMinGoal = findViewById(R.id.editTextMinGoal)
        editTextMaxGoal = findViewById(R.id.editTextMaxGoal)
        editTextDate = findViewById(R.id.editTextDate)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            saveGoals()
        }

    }//end of method

    private fun saveGoals() {
        val minGoalText = editTextMinGoal.text.toString()
        val maxGoalText = editTextMaxGoal.text.toString()
        val dateText = editTextDate.text.toString()


        if (minGoalText.isNotEmpty() && maxGoalText.isNotEmpty() && dateText.isNotEmpty()) {
            val minGoal = minGoalText.toFloat()
            val maxGoal = maxGoalText.toFloat()

            val message = "Minimum Goal: $minGoal hours\nMaximum Goal: $maxGoal hours\nDate: $dateText"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            notifyIfGoalsReached(minGoal, maxGoal)
        } else {
            Toast.makeText(this, "Please enter minimum and maximum goals, and date", Toast.LENGTH_SHORT).show()
        }
    }

    private fun notifyIfGoalsReached(minGoal: Float, maxGoal: Float) {
        TODO("Not yet implemented")
    }

    private fun notifyIfGoalsReached(minGoal: Float, maxGoal: Float, totalHoursWorked: Float) {

        if (totalHoursWorked > maxGoal) {

            Toast.makeText(this, "You've exceeded your maximum daily goal!", Toast.LENGTH_SHORT).show()
        }

        if (totalHoursWorked < minGoal) {

            Toast.makeText(this, "You've not reached your minimum daily goal!", Toast.LENGTH_SHORT).show()
        }
    }


}//en of class