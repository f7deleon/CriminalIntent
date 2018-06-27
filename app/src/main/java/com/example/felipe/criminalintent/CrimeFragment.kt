package com.example.felipe.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_crime.view.*

class CrimeFragment : Fragment() {
    private var crime = Crime()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_crime, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        with(view) {
            chkSolved.setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }

            btnCrime.text = crime.date.toString()
            titleEditText.afterTextChangeListener {
                crime.title = it.toString()
            }
        }
    }

    private fun EditText.afterTextChangeListener(
            afterTextChange: ((s: Editable) -> Unit)) = addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable) = afterTextChange(s)

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
