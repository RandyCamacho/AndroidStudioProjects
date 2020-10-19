package com.example.project1.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.project1.Event.fragmentEvents

import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.Query
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import android.util.Half.greater
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import com.example.project1.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InputFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InputFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var userBox: Box<user>
    private lateinit var query: Query<user>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        userBox = ObjectBox.boxStore.boxFor()
        query = userBox.query {
            order(user_.username)
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.project1.R.layout.fragment_input, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            InputFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun loginEvent(event:fragmentEvents){
        Log.d("RC", "Got into event function")
        if (view != null){
            val myView = view as View
            val usernameText: EditText = myView.findViewById(com.example.project1.R.id.editText_username)
            val passText: EditText = myView.findViewById(com.example.project1.R.id.editText_password)
            val error: TextView = myView.findViewById(R.id.error_login)

            val usernameInput = usernameText.text.toString()
            val passInput = passText.text.toString()


            val query = userBox.query()
                query.equal(user_.username, usernameInput)
                    .equal(user_.password, passInput)
            val account = query.build().find()

            if (account.isEmpty()) {

                    error.setText("Invalid Username or Password", TextView.BufferType.NORMAL)
                    //val intent = Intent(this@InputFragment.context, signUpActivity::class.java)
                   // startActivity(intent)
            } else {
                val intent = Intent(this@InputFragment.context, profileActivity::class.java)
                startActivity(intent)
            }

            Log.d("RC","username=$usernameInput")
            Log.d("RC","password=$passInput")


        }
    }
}