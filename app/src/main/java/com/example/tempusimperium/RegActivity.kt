package com.example.tempusimperium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegActivity : AppCompatActivity() {

    lateinit var enEmail: EditText
    lateinit var ConPass: EditText
    private lateinit var crPass: EditText
    private lateinit var btnReggie: Button
    lateinit var txtRedirectLog: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

//text binding
        enEmail = findViewById(R.id.mailInput)
        ConPass = findViewById(R.id.txtConPass)
        crPass = findViewById(R.id.inputPass)
        btnReggie = findViewById(R.id.btnReg)
        txtRedirectLog = findViewById(R.id.txtRedirect)
        auth = Firebase.auth

        btnReggie.setOnClickListener {
            signUpUser()
        }

        txtRedirectLog.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }//end of mainmethod

    private fun signUpUser() {
        val email = enEmail.text.toString()
        val pass = crPass.text.toString()
        val confirmPassword = ConPass.text.toString()

        //ensures that fields arent blank
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        //Checkspassword length
        if (pass.length < 8) {
            Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if password and confirm password match
        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }


        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods ?: emptyList<String>()
                if (signInMethods.isNotEmpty()) {
                    //for existing user, sends them to login
                    Toast.makeText(this, "Email is already in use", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    //new useer goes to onboarding screens
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { signUpTask ->
                        if (signUpTask.isSuccessful) {
                            Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, OnBoarding1::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Sign Up Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Failed to check email existence", Toast.LENGTH_SHORT).show()
            }
        }
    }


}//end of class