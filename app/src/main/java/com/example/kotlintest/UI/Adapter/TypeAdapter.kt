package com.example.kotlintest.UI.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.Beens.CoinInfoBeens
import com.example.kotlintest.R

class TypeAdapter(val  beens:List<CoinInfoBeens.CoinInfo>, val itemClickListener: (CoinInfoBeens.CoinInfo)->Unit) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    var i =0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apt_type, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int {
        return  beens.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(i==position){
                holder.view.setBackgroundColor(Color.BLUE)
            }else{
                holder.view.setBackgroundColor(Color.WHITE)
            }
            holder.view.setOnClickListener {
                    itemClickListener(beens[position])
                    i=position
                    notifyDataSetChanged()
            }
    }

    class ViewHolder(val view: View, var itemClickListener: (CoinInfoBeens.CoinInfo) -> Unit) : RecyclerView.ViewHolder(view){

    }

}

