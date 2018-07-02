package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.KeyEvent
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

    private var fragment: CrimeFragment? = null
    override fun initFragments() {
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        fragment = CrimeFragment.newInstance(crimeId)
        fragment?.addFragmentToFragmentContainer()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            fragment?.onKeyDown()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }
}
