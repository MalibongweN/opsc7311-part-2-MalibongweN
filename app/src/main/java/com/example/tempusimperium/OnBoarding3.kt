package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OnBoarding3 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)


        val GSbtn: Button = this.findViewById(R.id.GSbtn)
        GSbtn.setOnClickListener {
            moveToNextActivity()
        }

        val floActionbac3: FloatingActionButton = this.findViewById(R.id.floActionbac3)
        floActionbac3.setOnClickListener {
            moveToPrevActivity()
        }


    }//end of main method

    private fun moveToNextActivity() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToPrevActivity() {

        val intent = Intent(this, OnBoarding2::class.java)
        startActivity(intent)
        finish()
    }

}//end of class