package com.example.finalcutbooking

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.login_button)
        val skipLogin: TextView = findViewById(R.id.skiplogin_textview)

        val contact: TextView = findViewById(R.id.contact_textView)

        contact.setText("4414 W. Overland Rd. Boise, Idaho 83705" + "\n" + "Phone: (208)695-8504")

        loginButton.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        skipLogin.setOnClickListener {
            val intent = Intent(this, homeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
