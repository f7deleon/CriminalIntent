package com.example.felipe.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class SingleFragmentActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        initFragments()
    }

    abstract fun initFragments()

    protected fun Fragment.addFragmentToFragmentContainer() = supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, this)
            .commit()
}
