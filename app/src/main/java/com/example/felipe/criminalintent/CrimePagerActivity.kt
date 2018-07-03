package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crime_pager.*
import android.content.Intent
import android.view.KeyEvent
import java.util.UUID

class CrimePagerActivity : AppCompatActivity() {
    companion object {
        val EXTRA_CRIME_ID = "${CrimePagerActivity::class.java.canonicalName}.crime_id"

        fun newIntent(packageContext: Context?, crimeId: UUID): Intent {
            return Intent(packageContext, CrimePagerActivity::class.java).putExtra(EXTRA_CRIME_ID, crimeId)
        }
    }

    private var updatedCrimes = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        val crimes = CrimeController.getInstance().listCrimes()
        val fragmentManager = supportFragmentManager
        val adapter = CrimePageAdapter(crimes, { updatedCrimes.add(it.id.toString()) }, fragmentManager)
        crimeViewPager.adapter = adapter

        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as? UUID?:UUID.fromString("")
        crimeViewPager.currentItem = adapter.getItemPosition(crimeId)

        btnFirst.setOnClickListener {
            crimeViewPager.currentItem = 0
        }
        btnLast.setOnClickListener {
            crimeViewPager.currentItem = (crimeViewPager.adapter as? CrimePageAdapter)?.count?:0
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent()
            intent.putStringArrayListExtra(CrimeFragment.EXTRA_CRIME_ID, updatedCrimes)
            finishActivityWithResult(intent)
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }
}
