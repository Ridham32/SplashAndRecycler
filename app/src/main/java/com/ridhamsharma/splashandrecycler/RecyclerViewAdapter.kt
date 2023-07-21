package com.ridhamsharma.splashandrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var student:ArrayList<DataClass>,var recyclerInterface:recyclerinterface) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> (){
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var tvName = view.findViewById<TextView>(R.id.tvName)
       var tvRollNo = view.findViewById<TextView>(R.id.tvRollNo)
        var btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return student.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //values set
        holder.tvName.setText("position $position")
       holder.tvRollNo.setText(" position $position")
        holder.tvRollNo.setText(student[position].rollno.toString())
        holder.tvName.setText(student[position].name)
  holder.btnUpdate.setOnClickListener {
      recyclerInterface.onUpdateClick(position)
  }


    }
}