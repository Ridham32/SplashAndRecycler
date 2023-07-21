package com.ridhamsharma.splashandrecycler

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridhamsharma.splashandrecycler.databinding.ActivityMainBinding
import com.ridhamsharma.splashandrecycler.databinding.CustomdialogfabBinding

class MainActivity : AppCompatActivity(),recyclerinterface {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter
    var student = arrayListOf<DataClass>()
    lateinit var layoutManager: LinearLayoutManager
    lateinit var notesDbObj1:NotesDb



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //default orientation:- vertical
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager = LinearLayoutManager(this)
        //cannot make instance of an abstract class
       //----> notesDbObj1= NotesDb()
        //therefore initialise the class------
        notesDbObj1= NotesDb.getNotesDatabase(this)
        adapter = RecyclerViewAdapter(student, this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        //to set the desired orientation
        /*** LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) ***/


        binding.fab.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = CustomdialogfabBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            );
            dialogBinding.btnCustomAdd.setOnClickListener {
                if (dialogBinding.etCustomtitle.text.toString().isNullOrEmpty()) {
                    dialogBinding.etCustomtitle.error = "Enter The Title"
                } else if (dialogBinding.etCustomDescription.text.toString().isNullOrEmpty()) {
                    dialogBinding.etCustomDescription.error = "Enter The Description"
                } else {
                    student.add(
                        DataClass(
                            dialogBinding.etCustomtitle.text.toString(),
                            dialogBinding.etCustomDescription.text.toString().toInt()
                        )
                    )

                    adapter.notifyDataSetChanged()
                    dialog.dismiss()

                }
            }
            dialog.show()
        }

    }

    override fun onDeleteClick(position: Int) {
        student.removeAt(position)
        adapter.notifyDataSetChanged()

    }

    override fun onUpdateClick(position: Int) {
        var dialog = Dialog(this)
        var dialogBinding = CustomdialogfabBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.getWindow()?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialogBinding.btnCustomAdd.setOnClickListener {
            if (dialogBinding.etCustomtitle.text.toString().isNullOrEmpty()){
                dialogBinding.etCustomtitle.error="Enter Your Name"
            } else if (dialogBinding.etCustomDescription.text.toString().isNullOrEmpty()) {
                dialogBinding.etCustomDescription.error = "Enter Your Rollno"
            } else {
                student.add(
                    DataClass(
                        dialogBinding.etCustomtitle.text.toString(),
                        dialogBinding.etCustomDescription.text.toString().toInt()
                    )
                )

                adapter.notifyDataSetChanged()
                dialog.dismiss()

            }
        }
        dialog.show()
    }
        }







