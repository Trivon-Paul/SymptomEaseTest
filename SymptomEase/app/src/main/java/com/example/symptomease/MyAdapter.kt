package com.example.symptomease

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val symptomName: MutableList<String>,
                private val symptomDescription: MutableList<String>):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
          val symptomName=itemView.findViewById<TextView>(R.id.symptomName)
          val symptomDescription=itemView.findViewById<TextView>(R.id.symptomDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.row_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return symptomName.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.symptomName.text = symptomName.elementAt(position)
        holder.symptomDescription.text = symptomDescription.elementAt(position)
    }
}