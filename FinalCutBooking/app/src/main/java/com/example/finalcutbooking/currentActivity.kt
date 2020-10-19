package com.example.finalcutbooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_current.*

class currentActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var booking_list: MutableList<Book>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        booking_list = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("booking").child(userId)
        listView = findViewById(R.id.booking_listview)

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                        for(h in p0.children) {
                            val book = h.getValue(Book::class.java)
                            booking_list.add(book!!)
                        }

                        val adapter = bookAdapter(applicationContext, R.layout.book, booking_list)
                        listView.adapter = adapter
                }
            }
        })

    }
}
