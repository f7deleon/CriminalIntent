package com.example.felipe.criminalintent

import android.os.Bundle
import android.os.PersistableBundle

class CrimeActivity : SingleFragmentActivity() {
    override fun initFragments() {
        CrimeFragment().addFragmentToFragmentContainer()
    }
}
