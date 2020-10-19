package com.example.project1

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.project1.fragments.BannerFragment

class profileActivity : AppCompatActivity(), BannerFragment.OnFragmentInteractionListener {

    lateinit var bannerFragment: BannerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bannerFragment = BannerFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.banner, bannerFragment)
            .addToBackStack("banner")
            .setTransition(FragmentTransaction.TRANSIT_NONE)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
