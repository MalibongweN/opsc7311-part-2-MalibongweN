package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEventSource
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgActivities = findViewById<ImageView>(R.id.imgActivities)
        val imgDailyGoals = findViewById<ImageView>(R.id.imgDailyGoals)
        val imgTimer = findViewById<ImageView>(R.id.imgTimer)
        val imgProductivity = findViewById<ImageView>(R.id.imgProductivity)

        imgActivities.setOnClickListener(View.OnClickListener { this })
        imgDailyGoals.setOnClickListener(View.OnClickListener { this })
        imgTimer.setOnClickListener(View.OnClickListener { this })
        imgProductivity.setOnClickListener(View.OnClickListener { this })

    }

    private  fun onClick(view: View?) {

        when (view?.id) {
            R.id.imgActivities -> startActivity(Intent(this, Activities::class.java))
            R.id.imgDailyGoals -> startActivity(Intent(this, DailyGoal::class.java))
            R.id.imgTimer -> startActivity(Intent(this, Timer::class.java))
            R.id.imgProductivity -> startActivity(Intent(this, Productivity::class.java))
        }
    }

}


