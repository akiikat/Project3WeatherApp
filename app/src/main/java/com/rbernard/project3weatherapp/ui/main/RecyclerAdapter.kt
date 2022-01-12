package com.rbernard.project3weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.rbernard.project3weatherapp.R
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val items: ArrayList<Items>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val itemIcon = itemView.findViewById<ImageView>(R.id.icon5)
            val itemDate = itemView.findViewById<TextView>(R.id.date5)
            val itemTemp = itemView.findViewById<TextView>(R.id.temp5)
    }//end ViewHolder Class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent .context)
            .inflate(R.layout.card_layout,parent,false) as CardView
        return ViewHolder(v)
    }//end onCreateViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //have to use picasso to bring photo over
       Picasso.with(holder.itemView.context)
            .load(items[position].icon).resize(100,100).into(holder.itemIcon)
        //set other cardView textViews
        holder.itemDate.text = items[position].date
        holder.itemTemp.text = items[position].temp
    }//end onBindViewHolder


    //method that returns the # of items in the array
    override fun getItemCount(): Int {
        //returns the size of JSON 5day/7day forecast array from OneCallApi
        return items.size
    }//end getItemCount


}//end recycleView class