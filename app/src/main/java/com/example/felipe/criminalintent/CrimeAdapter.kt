package com.example.felipe.criminalintent

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_crime.view.*
import java.util.UUID

class CrimeAdapter(val crimes: List<Crime>, var activity: FragmentActivity?) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val layoutInflater = LayoutInflater.from(activity)
        return CrimeHolder(layoutInflater, parent, activity = activity, layout = R.layout.list_item_crime)
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        holder.bind(crimes[position])
    }

    override fun getItemCount() = crimes.size

    fun notifyItemChangedByID(index : UUID?){
        this.notifyItemChanged(CrimeController.getInstance().getIndex(index))
    }
}

open class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup, layout: Int, val activity: FragmentActivity?) :
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

    override fun onClick(view: View) {
        val intent = CrimeActivity.newIntent(activity, crime?.id)
        activity?.startActivityForResult(intent,CrimeListFragment.REQUEST_CRIME)
    }
}
