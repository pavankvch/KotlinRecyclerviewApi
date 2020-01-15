package com.sample.kotlinsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.kotlinsample.R
import com.sample.kotlinsample.model.Data
import kotlinx.android.synthetic.main.inflate_list.view.*

class MyAdapter(var context: Context, var listOfEmployes: List<Data>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.inflate_list, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = "Name : " + listOfEmployes.get(position).first_name
        holder.email.text = "Id : " + listOfEmployes.get(position).email

        Glide.with(context).load(listOfEmployes.get(position).avatar).into(holder.img)

        holder.itemView.setOnClickListener {
            //            applicationContext.toast(list.get(position).original_title)
        }
    }

    override fun getItemCount(): Int {
        return listOfEmployes.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val img = itemView.imgEmployee
        val email = itemView.tvEmail
    }


}
