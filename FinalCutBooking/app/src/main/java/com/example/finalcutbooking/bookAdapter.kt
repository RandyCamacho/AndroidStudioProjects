package com.example.finalcutbooking

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class bookAdapter(val mCtx: Context, val layoutResId: Int, val booking_list: List<Book>)
    :ArrayAdapter<Book>(mCtx, layoutResId, booking_list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(layoutResId, null)

        val name = view.findViewById<TextView>(R.id.textName)
        val date = view.findViewById<TextView>(R.id.textDate)
        val time = view.findViewById<TextView>(R.id.textTime)
        val phone = view.findViewById<TextView>(R.id.textPhone)

        val book = booking_list[position]

        name.text = book.name
        date.text = book.date
        time.text = book.time
        phone.text = book.phone

        val delete = view.findViewById<TextView>(R.id.textDelete)

        delete.setOnClickListener {
            deleteBook(book)
        }

        return view
    }

    private fun deleteBook(book: Book) {
        val userId = FirebaseAuth.getInstance().getCurrentUser()!!.getUid()
        val mydatabase = FirebaseDatabase.getInstance().getReference("booking")
        mydatabase.child(userId).child(book.bookId).removeValue()
        Toast.makeText(mCtx,"Your Booking Has Been Deleted", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, profileActivity::class.java)
        context.startActivity(intent)
    }
}