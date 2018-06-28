package com.example.felipe.criminalintent

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeAdapter(var crimes: List<Crime>, var activity: FragmentActivity?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        return when (viewType) {
            ViewType.POLICE.ordinal -> CrimeHolderPolice(layoutInflater, parent)
            else -> CrimeHolder(layoutInflater, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ViewType.NORMAL.ordinal -> (holder as? CrimeHolder)?.bind(crimes[position])
            ViewType.POLICE.ordinal -> (holder as? CrimeHolderPolice)?.bind(crimes[position])
        }
    }

    override fun getItemViewType(position: Int): Int = when (crimes[position].isPoliceRequire) {
        true -> ViewType.POLICE.ordinal
        false -> ViewType.NORMAL.ordinal
    }

    override fun getItemCount() = crimes.size

    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false)),
            View.OnClickListener {

        private var crime: Crime? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime
            itemView.txtCrimeTitle.text = crime.title
            itemView.txtCrimeDate.text = crime.date.toString()
        }

        override fun onClick(view: View) { Toast.makeText(activity, "${crime?.title} clicked!", Toast.LENGTH_SHORT).show() }

    }

    private inner class CrimeHolderPolice(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime_police, parent, false)),
            View.OnClickListener {

        private var crime: Crime? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime
            itemView.txtCrimeTitle.text = crime.title
            itemView.txtCrimeDate.text = crime.date.toString()
        }

        override fun onClick(view: View) { Toast.makeText(activity, "${crime?.title} clicked!", Toast.LENGTH_SHORT).show() }
    }
}

private enum class ViewType {
    NORMAL, POLICE
}
