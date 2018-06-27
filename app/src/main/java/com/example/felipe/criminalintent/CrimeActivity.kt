package com.example.felipe.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class CrimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)
        initFragments()
    }

    private fun initFragments() {
        var fragment = CrimeFragment()
        fragment.doTransaction()
    }

    private fun Fragment.doTransaction() = supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,this).commit()
}
