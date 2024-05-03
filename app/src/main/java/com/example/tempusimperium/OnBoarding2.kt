package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OnBoarding2 : AppCompatActivity() {

    val onboard2: Long = 10000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding2)

        Handler(Looper.getMainLooper()).postDelayed({
            moveToNextActivity()
        }, onboard2)

        val floActionBtn2: FloatingActionButton = this.findViewById(R.id.floActionBtn2)
        floActionBtn2.setOnClickListener {
            moveToNextActivity()
        }

        val floActionBac1: FloatingActionButton = this.findViewById(R.id.floActionBac1)
        floActionBac1.setOnClickListener {
            moveTopPrevActivity()
        }

        val sc2skip = findViewById<TextView>(R.id.sc2skip)
        sc2skip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }//END OF MAIN METHOD

    //move to next activity
    private fun moveToNextActivity() {

        val intent = Intent(this, OnBoarding3::class.java)
        startActivity(intent)
        finish()
    }
//move to previous activity
    private fun moveTopPrevActivity() {

        val intent = Intent(this, OnBoarding1::class.java)
        startActivity(intent)
        finish()
    }



}//ENd OF CLASS