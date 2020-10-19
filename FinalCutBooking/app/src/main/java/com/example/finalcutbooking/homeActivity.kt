package com.example.finalcutbooking

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class homeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val description_home: TextView = findViewById(R.id.description_textview)

        val loginButton:Button = findViewById(R.id.login_button)
        val directions:Button = findViewById(R.id.directions_button)

        description_home.setText("The Final Cut Barbershop \n offers Men’s Services \n by a Solid Team. \n Atmosphere is Welcoming and We \n Cater to your needs.\n" +
                "\n" +
                "\t\tServices:\n" +
                "\n" +
                "Men’s Standard Haircut - \$20.00\n" +
                "Men’s Fade - \$ 25.00\n" +
                "Men’s Fade and Bear - \$28.00\n" +
                "Kid’s Fade - \$15.00\n" +
                "Fade and Hair Design - \$30.00\n")

        loginButton.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
                finish()
        }

        directions.setOnClickListener {
            val intent = Intent(this, directionsActivity::class.java)
            startActivity(intent)
        }

    }
}
