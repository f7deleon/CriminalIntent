package com.example.felipe.criminalintent

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_crime_list.view.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        view.recycleViewCrimes.layoutManager = LinearLayoutManager(activity)
        val crimeController = CrimeController.SingletonClass.getInstance()
        view.recycleViewCrimes.adapter = CrimeAdapter(crimeController.listCrimes())
        return view
    }

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

        override fun onClick(view: View) {
            Toast.makeText(activity, "${crime?.title} clicked!", Toast.LENGTH_SHORT).show()
        }
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

        override fun onClick(view: View) {
            Toast.makeText(activity, "${crime?.title} clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return when (viewType) {
                1 -> CrimeHolderPolice(layoutInflater, parent)
                else -> CrimeHolder(layoutInflater, parent)
            }

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder.itemViewType) {
                0 -> {
                    holder as CrimeHolder
                    val crime = crimes[position]
                    holder.bind(crime)
                }
                1 -> {
                    holder as CrimeHolderPolice
                    val crime = crimes[position]
                    holder.bind(crime)
                }
            }
        }

        override fun getItemViewType(position: Int): Int = when (crimes[position].isPoliceRequire) {
            true -> 1
            false -> 0
        }

        override fun getItemCount() = crimes.size
    }
}