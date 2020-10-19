package com.example.finalcutbooking

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bookNow_button.setOnClickListener {
            val intent = Intent(this, bookingActivity::class.java)
            startActivity(intent)
        }

        current_button.setOnClickListener {
            val intent = Intent(this, currentActivity::class.java)
            startActivity(intent)
        }

        pDirections_button.setOnClickListener {
            val intent = Intent(this, directionsActivity::class.java)
            startActivity(intent)
        }

        signOut_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
        }
    }
}
