package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var txtRedirect: TextView
    lateinit var logMail: EditText
    lateinit var logPass: EditText
    lateinit var btnLogin: Button
    lateinit var auth: FirebaseAuth
    private var loginAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //text binding
        txtRedirect = findViewById(R.id.navToReg)
        btnLogin = findViewById(R.id.btnLog)
        logMail = findViewById(R.id.logEmail)
        logPass = findViewById(R.id.txtPassword)

        //initialise Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        txtRedirect.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
            finish()
        }
    }//end of method

    private fun login() {
        val email = logMail.text.toString()
        val pass = logPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                loginAttempts++
                if (loginAttempts >= 3) {
                    Toast.makeText(this, "Login attempts exceeded. Calling the cops.", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                } else {
                    Toast.makeText(this, "Login incorrect. Attempts left: ${3 - loginAttempts}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}//end of class