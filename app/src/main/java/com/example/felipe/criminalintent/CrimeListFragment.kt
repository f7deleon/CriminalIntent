package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime_list.view.*
import java.util.UUID

class CrimeListFragment : Fragment() {
    companion object {
        const val ID_EDITED_CRIME = 1
    }

    private var adapter: CrimeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        if (view?.recycleViewCrimes?.adapter == null) {
            view?.recycleViewCrimes?.layoutManager = LinearLayoutManager(activity)
            val crimeController = CrimeController.getInstance()
            adapter = CrimeAdapter(crimeController.listCrimes()) {
                val intent = CrimeActivity.newIntent(activity, it.id)
                startActivityForResult(intent, ID_EDITED_CRIME)
            }
            view?.recycleViewCrimes?.adapter = adapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == ID_EDITED_CRIME) {
            val id = data?.getStringExtra(CrimeFragment.EXTRA_CRIME_ID)
            val idu = UUID.fromString(id)
            adapter?.notifyItemChangedByID(idu)
        }
    }
}
