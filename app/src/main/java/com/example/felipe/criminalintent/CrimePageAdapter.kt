package com.example.felipe.criminalintent

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import java.util.UUID

class CrimePageAdapter(crimes: List<Crime>,
                       val onCrimeUpdated: (Crime) -> Unit,
                       fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {
    private val crimes = ArrayList<Crime>(crimes)

    override fun getItem(position: Int): Fragment {
        val crime = crimes.get(position)
        return CrimeFragment.newInstance(crime.id, onCrimeUpdated)
    }

    override fun getCount(): Int = crimes.size

    fun getItemPosition(crime_id: UUID) = crimes.indexOfFirst { it.id == crime_id }
}
