package com.example.felipe.criminalintent

import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class CrimeListActivity : SingleFragmentActivity() {
    override fun initFragments() {
        Fabric.with(this, Crashlytics())
        CrimeListFragment().addFragmentToFragmentContainer()
    }
}
