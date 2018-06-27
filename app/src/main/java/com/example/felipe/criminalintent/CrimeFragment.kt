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
            editTxtTitle.addTextChangedListener { s, _, _, _ ->
                crime.title = s
            }
        }
    }

    private fun EditText.addTextChangedListener(after: (s: Editable) -> Unit = {},
                                                before: (string: String, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
                                                onTextChanged: (string: String, start: Int, count: Int, after: Int) -> Unit) =
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) = after.invoke(s)

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
                        before.invoke(s.toString(), start, count, after)

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = onTextChanged(s.toString(), start, before, count)
            })
}
