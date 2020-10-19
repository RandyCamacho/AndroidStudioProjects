package com.example.finalcutbooking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Script
import android.telephony.PhoneNumberUtils
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_booking.*
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class bookingActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        ref = FirebaseDatabase.getInstance().getReference("booking")

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        date_button.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, mYear: Int, mMonth: Int, mDay: Int ->
                date_textview.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
            }, year, month, day)

            dpd.show()
        }

        time_button.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)

                time_textview.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        save_button.setOnClickListener {
            saveBooking()
        }

        cancel_button.setOnClickListener {
           // val intent = Intent(this, profileActivity::class.java)
            //startActivity(intent)
            finish()
        }
    }

        private fun saveBooking(){
            val name = bookName_editText.text.toString()
            val phone: EditText = findViewById(R.id.phone_editText)
            val number = phone.text.toString()
            PhoneNumberUtils.formatNumber(number)

            val date = date_textview.text.toString()
            val time = time_textview.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
            } else if (number.isEmpty()){
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
            } else if (number.length != 10 ){
                Toast.makeText(this, "Enter 10 Digit Phone Number", Toast.LENGTH_SHORT).show()
            } else if(date.isEmpty()){
                Toast.makeText(this, "Choose a Date", Toast.LENGTH_SHORT).show()
            } else if(time.isEmpty()){
                Toast.makeText(this, "Choose a Time", Toast.LENGTH_SHORT).show()
            } else {
                val uId = FirebaseAuth.getInstance().currentUser!!.uid
                val bookId = ref.push().key.toString()
                val book = Book(bookId, name, number, date, time)

                ref.child(uId).child(bookId).setValue(book).addOnCompleteListener {
                    Toast.makeText(this, "Thanks For Booking", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }


}
