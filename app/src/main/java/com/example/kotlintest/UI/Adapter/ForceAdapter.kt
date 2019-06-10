package com.example.kotlintest.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.R
import kotlinx.android.synthetic.main.apt_force.view.*
import kotlinx.android.synthetic.main.apt_type.view.*

class ForceAdapter(val  title:Array<String>,val  data:Array<String>) : RecyclerView.Adapter<ForceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apt_force, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  title.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(title[position],data[position])

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(title: String,data:String){
            view.tv_force_title.text=title
            view.tv_force_data.text=data
        }
    }

}