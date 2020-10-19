package com.example.project1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.Query

class signUpActivity : AppCompatActivity() {

    private lateinit var userBox: Box<user>
    private lateinit var query: Query<user>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

            userBox = ObjectBox.boxStore.boxFor()
            query = userBox.query {
                order(user_.username)
            }

            val createUserButton:Button = findViewById(R.id.button_save)

            createUserButton.setOnClickListener {
                createUser()
            }
    }

    private fun createUser() {
        val username: EditText = findViewById(R.id.editText_sUsername)
        val password: EditText = findViewById(R.id.editText_sPassword)
        val confirmPassword: EditText = findViewById(R.id.editText_cPassword)
        val errorUsername: TextView = findViewById(R.id.textView_errorUsername)
        val errorPassword: TextView = findViewById(R.id.textView_errorPassword)

        val newUsername = username.text.toString()
        val newPassword = password.text.toString()
        val cPassword = confirmPassword.text.toString()

        val newUser = user(username = newUsername, password = newPassword)
        if(newUsername == ""){
            errorUsername.setText("Enter a Username", TextView.BufferType.NORMAL)
        }  else if (newPassword == "" || cPassword == ""){
            errorPassword.setText("Enter a Password", TextView.BufferType.NORMAL)
        } else if(newPassword != cPassword){
            errorPassword.setText("Passwords Do Not Match", TextView.BufferType.NORMAL)
        } else {
            userBox.put(newUser)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
