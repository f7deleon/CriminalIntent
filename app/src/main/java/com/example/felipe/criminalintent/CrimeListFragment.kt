package com.example.felipe.criminalintent

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime_list.view.*


class CrimeListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        initializateCrimeList(view)
        return view
    }

    private fun initializateCrimeList(view: View) {
        view.recycleViewCrimes.layoutManager = LinearLayoutManager(activity)
        val crimeController = CrimeController.getInstance()
        view.recycleViewCrimes.adapter = CrimeAdapter(crimeController.listCrimes(), activity)
    }
}
