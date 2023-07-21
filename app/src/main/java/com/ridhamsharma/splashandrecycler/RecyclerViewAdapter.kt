package com.ridhamsharma.splashandrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var student:ArrayList<NotesEntityDataClass>,var recyclerInterface:recyclerinterface) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> (){
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var tvTitle = view.findViewById<TextView>(R.id.tvTitle)
       var tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        var btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        var btnDelete=view.findViewById<Button>(R.id.btnDelete)
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
        holder.tvTitle.setText("position $position")
       holder.tvDescription.setText(" position $position")
        holder.tvDescription.setText(student[position].description)
        holder.tvTitle.setText(student[position].title)
  holder.btnUpdate.setOnClickListener {
      recyclerInterface.onUpdateClick(position)
  }
        holder.btnDelete.setOnClickListener{
            recyclerInterface.onDeleteClick(position)
        }


    }
}