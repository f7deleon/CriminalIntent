package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_crime.view.*
import java.util.UUID

class CrimeFragment : Fragment() {
    companion object {
        const val ARG_CRIME_ID = "crime_id"
        val EXTRA_CRIME_ID = "${CrimeFragment::class.java.canonicalName}.crime_id"

        fun newInstance(crimeId: UUID): CrimeFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARG_CRIME_ID, crimeId)
            val fragment = CrimeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var crime : Crime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crimeId = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crime = CrimeController.getInstance().getCrime(crimeId) ?: Crime()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        with(view) {
            chkSolved.isChecked = crime?.isSolved?:false
            chkPoliceRequired.isChecked = crime?.isPoliceRequire?:false
            titleEditText.setText(crime?.title)
            btnCrime.text = crime?.date.toString()
            chkSolved.setOnCheckedChangeListener { _, isChecked ->
                crime?.isSolved = isChecked
            }
            chkPoliceRequired.setOnCheckedChangeListener { _, isChecked ->
                crime?.isPoliceRequire = isChecked
            }
            titleEditText.afterTextChangeListener {
                crime?.title = it.toString()
            }
        }
    }

    fun onKeyDown() {
        val intent = Intent()
        intent.putExtra(EXTRA_CRIME_ID, crime?.id.toString())
        activity?.finishActivityWithResult(intent)
    }

    private fun EditText.afterTextChangeListener(
            afterTextChange: ((s: Editable) -> Unit)) = addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable) = afterTextChange(s)

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
