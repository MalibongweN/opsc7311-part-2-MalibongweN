package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OnBoarding1 : AppCompatActivity() {

    val onboard1: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        Handler(Looper.getMainLooper()).postDelayed({
            moveToNextActivity()
        }, onboard1)

        val flAction1: FloatingActionButton = this.findViewById(R.id.flAction1)
        flAction1.setOnClickListener {
            moveToNextActivity()
        }

        val sc1skip = findViewById<TextView>(R.id.sc1skip)
        sc1skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }//end of mainmethod

    private fun moveToNextActivity() {
        // Move to the next activity
        val intent = Intent(this, OnBoarding2::class.java)
        startActivity(intent)
        finish()
    }

}//end of class