package com.example.felipe.criminalintent

import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_crime.view.*
import java.util.UUID

class CrimeAdapter(val crimes: List<Crime>, private val callback: (Crime) -> Unit) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CrimeHolder(layoutInflater, parent, layout = R.layout.list_item_crime, callback = callback)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount() = crimes.size

    fun notifyItemChangedByID(index: UUID?) {
        this.notifyItemChanged(CrimeController.getInstance().getIndex(index))
    }
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
