package com.example.felipe.criminalintent

import android.support.annotation.MainThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_crime.view.*
import java.util.UUID

class CrimeAdapter(crimes: List<Crime>, private val callback: (Crime) -> Unit) : RecyclerView.Adapter<CrimeHolder>() {
    private val crimes = ArrayList<Crime>(crimes)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
       return CrimeHolder(layoutInflater, parent, layout = R.layout.list_item_crime, callback = callback)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount() = crimes.size

    @MainThread
    fun notifyItemChangedByID(ids: List<String>) {
        ids.asSequence().forEach {
            notifyItemChanged(getCrimeIndex(it))
        }
    }

    private fun getCrimeIndex(str :String): Int = crimes.indexOfFirst{ it.id == UUID.fromString(str)}
}

class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup, layout: Int, private val callback: (Crime) -> Unit) :
        RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)),
        View.OnClickListener {
    private var crime: Crime? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(crime: Crime) {
        this.crime = crime
        itemView.txtCrimeTitle.text = crime.title
        itemView.txtCrimeDate.text = crime.date.toString()
        if (crime.isSolved) itemView.imgSolvedCrime.visibility = View.VISIBLE
        if (crime.isPoliceRequire) itemView.imgPoliceRequired.visibility = View.VISIBLE
    }

    override fun onClick(view: View) = crime?.let { callback.invoke(it) } ?: Unit
}
