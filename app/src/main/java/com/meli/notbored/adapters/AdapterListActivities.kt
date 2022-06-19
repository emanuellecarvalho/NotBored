package com.meli.notbored.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meli.notbored.R
import com.meli.notbored.domain.Activity

class AdapterListActivities(
    val list: List<Activity>,
    val onClick: (Activity)->Unit):
    RecyclerView.Adapter<AdapterListActivities.ActivitityViewHolder>(){

    class ActivitityViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var container: LinearLayout = view.findViewById(R.id.container)
        var activities: TextView = view.findViewById(R.id.activities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_list, parent, false)
        return ActivitityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivitityViewHolder, position: Int) {
        val activity = list[position]
        holder.activities.text = activity.cativity
        holder.container.setOnClickListener(View.OnClickListener { onClick(activity) })
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}