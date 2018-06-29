package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent
import java.util.UUID

class CrimeActivity : SingleFragmentActivity() {
    companion object {
        val EXTRA_CRIME_ID = "${CrimeActivity::class.java.canonicalName}.crime_id"
        fun newIntent(activity: Activity?, id: UUID?): Intent {
            val intent = Intent(activity, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, id)
            return intent
        }
    }

    override fun initFragments() {
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        CrimeFragment().newInstance(crimeId).addFragmentToFragmentContainer()
    }
}
