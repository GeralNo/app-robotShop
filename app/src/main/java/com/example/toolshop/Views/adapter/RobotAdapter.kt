package com.example.toolshop.Views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toolshop.Models.robots
import com.example.toolshop.R
import com.squareup.picasso.Picasso

class RobotAdapter(val context: Context):RecyclerView.Adapter<RobotAdapter.ViewHolder>() {

    var robotList= mutableListOf<robots>()

    fun setListData(data:MutableList<robots>){
        robotList= data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_robot,
        ViewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val robot= robotList[i]
        viewHolder.bin(robot)
    }

    override fun getItemCount(): Int {
        return robotList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bin (robot:robots){

            Picasso.get().load(robot.url).into(itemView.findViewById<ImageView>(R.id.robotImg))
            itemView.findViewById<TextView>(R.id.robotModel).text= robot.model
            itemView.findViewById<TextView>(R.id.robotType).text= robot.type
            itemView.findViewById<TextView>(R.id.robotCompany).text= robot.company
            itemView.findViewById<TextView>(R.id.robotPrice).text= robot.price.toString()


        }
    }
}