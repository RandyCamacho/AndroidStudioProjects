package com.example.project1

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.project1.fragments.BannerFragment
import com.example.project1.fragments.ButtonsFragment
import com.example.project1.fragments.InputFragment

class MainActivity : AppCompatActivity(), ButtonsFragment.OnFragmentInteractionListener,
    InputFragment.OnFragmentInteractionListener, BannerFragment.OnFragmentInteractionListener{

    lateinit var bannerFragment:BannerFragment
    lateinit var inputFragment:InputFragment
    lateinit var buttonsFragment:ButtonsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerFragment = BannerFragment.newInstance()
        inputFragment = InputFragment.newInstance()
        buttonsFragment = ButtonsFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.banner, bannerFragment)
            .addToBackStack("banner")
            .setTransition(FragmentTransaction.TRANSIT_NONE)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.input, inputFragment)
            .addToBackStack("fragment")
            .setTransition(FragmentTransaction.TRANSIT_NONE)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.buttons, buttonsFragment)
            .addToBackStack("button")
            .setTransition(FragmentTransaction.TRANSIT_NONE)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
