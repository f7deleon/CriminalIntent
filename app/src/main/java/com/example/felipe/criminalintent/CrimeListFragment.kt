package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.fragment_crime_list.view.*
import java.util.UUID

class CrimeListFragment : Fragment() {
    companion object {
        const val EDIT_CRIME_REQUEST_CODE = 1
        const val NEW_CRIME_REQUEST_CODE = 2
    }

    private var adapter: CrimeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_crime_list, container, false)
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
                val intent = CrimePagerActivity.newIntent(context, it.id)
                startActivityForResult(intent, EDIT_CRIME_REQUEST_CODE)
            }
            view?.recycleViewCrimes?.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                CrimeController.getInstance().add(crime)
                val intent = CrimePagerActivity
                        .newIntent(activity, crime.id)
                startActivityForResult(intent, NEW_CRIME_REQUEST_CODE)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateSubtitle() {
        val crimeLab = CrimeController.getInstance()
        val crimeCount = crimeLab.size
        val subtitle = getString(R.string.subtitle_format, crimeCount)
        val activity = activity as? AppCompatActivity
        activity!!.supportActionBar!!.subtitle = subtitle
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == EDIT_CRIME_REQUEST_CODE) {
            val ids = data?.getStringArrayListExtra(CrimeFragment.EXTRA_CRIME_ID)
            adapter?.notifyItemChangedByID(ids ?: ArrayList())
        } else if (requestCode == NEW_CRIME_REQUEST_CODE) {
            adapter = CrimeAdapter(CrimeController.getInstance().listCrimes()) {
                val intent = CrimePagerActivity.newIntent(context, it.id)
                startActivityForResult(intent, EDIT_CRIME_REQUEST_CODE)
            }
            view?.recycleViewCrimes?.adapter = adapter
        }
    }
}
