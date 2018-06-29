package com.example.felipe.criminalintent

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.support.v4.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.content.Intent
import android.app.Activity
import kotlinx.android.synthetic.main.dialog_date.view.*
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar


class DatePickerFragment : DialogFragment() {
    companion object {
        val EXTRA_DATE = "${CrimeFragment::class.java.canonicalName}.date"
        const val ARG_DATE = "date"

        fun newInstance(date: Date?): DatePickerFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as? Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_date, null)
        view.crimeDatePicker.init(year, month, day, null)
        return AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(R.string.date_of_crime)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, wich ->
                    val year = view.crimeDatePicker.year
                    val month = view.crimeDatePicker.month
                    val day = view.crimeDatePicker.dayOfMonth
                    val date = GregorianCalendar(year, month, day).time
                    sendResult(Activity.RESULT_OK, date)

                })
                .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }

}
