package com.example.finalcutbooking

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class signUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signup: Button = findViewById(R.id.signup_button)
        val returnButton: TextView = findViewById(R.id.return_textview)

        signup.setOnClickListener {
            createUser()
        }

        returnButton.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun createUser(){
        val email = sEmail_textview.text.toString()
        val pass = sPass_textview.text.toString()
        val cPass = confirmPass_textview.text.toString()

        Log.d("signup", "email: $email")
        Log.d("signup", "Pass: $pass")
        Log.d("signup", "ConfirmPass: $cPass")

        if (email.isEmpty()){
            emailError_textview.setText("Enter an Email", TextView.BufferType.NORMAL)
        }
        if(pass.isEmpty() || cPass.isEmpty()) {
            passError_textview.setText("Enter a Password", TextView.BufferType.NORMAL)
        } else if(pass != cPass) {
            passError_textview.setText("Passwords Don't Match", TextView.BufferType.NORMAL)
        } else {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener

                    Log.d("signup", "User Successfully Created with uid: ${it.result.user.uid}")

                    val intent = Intent(this, profileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener{
                    emailError_textview.setText("Failed to Create Acount", TextView.BufferType.NORMAL)
                }
        }
    }

}