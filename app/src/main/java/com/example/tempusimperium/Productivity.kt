package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Productivity : AppCompatActivity() {
    val calmdown: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productivity)

        Handler(Looper.getMainLooper()).postDelayed({
            moveToNextActivity()
        }, calmdown)

    }

    private fun moveToNextActivity() {
        // Move to the next activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}