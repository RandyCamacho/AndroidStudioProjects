package com.example.finalcutbooking

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.checkLogin_button)
        val signup: TextView = findViewById(R.id.signUp_textview)

        signup.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = email_editText.text.toString()
        val password = password_textview.text.toString()

        Log.d("login", "email: $email")
        Log.d("login", "pass: $password")

        if (email.isEmpty()){
            errorLogin_textview.setText("Enter Email", TextView.BufferType.NORMAL)
        } else if(password.isEmpty()){
            errorLogin_textview.setText("Enter Password", TextView.BufferType.NORMAL)
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) {
                        return@addOnCompleteListener
                    } else {

                        val progressDialog = ProgressDialog(this,
                            R.style.Base_ThemeOverlay_AppCompat_Dialog_Alert)
                        progressDialog.isIndeterminate = true
                        progressDialog.setMessage("Authenticating...")
                        progressDialog.show()

                        val intent = Intent(this, profileActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                .addOnFailureListener {
                    errorLogin_textview.setText("Invalid Username Or Password", TextView.BufferType.NORMAL)
                }
        }
    }
}
