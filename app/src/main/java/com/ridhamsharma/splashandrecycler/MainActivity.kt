package com.ridhamsharma.splashandrecycler

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.text.method.TextKeyListener.clear
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridhamsharma.splashandrecycler.databinding.ActivityMainBinding
import com.ridhamsharma.splashandrecycler.databinding.CustomdialogfabBinding

class MainActivity : AppCompatActivity(), recyclerinterface {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RecyclerViewAdapter
    var student = arrayListOf<NotesEntityDataClass>()
    lateinit var layoutManager: LinearLayoutManager
    lateinit var notesDbObj1: NotesDb


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //default orientation:- vertical
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager = LinearLayoutManager(this)
        //cannot make instance of an abstract class
        //----> notesDbObj1= NotesDb()
        //therefore initialise the class------
        notesDbObj1 = NotesDb.getNotesDatabase(this)
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
            )
            dialogBinding.btnCustomAdd.setOnClickListener {

                if (dialogBinding.etCustomtitle.text.toString().isNullOrEmpty()) {
                    dialogBinding.etCustomtitle.error = "Enter The Title"
                } else if (dialogBinding.etCustomDescription.text.toString().isNullOrEmpty()) {
                    dialogBinding.etCustomDescription.error = "Enter The Description"
                } else {
                    class insert : AsyncTask<Void, Void, Void>() {
                        override fun doInBackground(vararg p0: Void?): Void? {
                            notesDbObj1.notesDao()
                                .insertNotes(
                                    NotesEntityDataClass(
                                        title = dialogBinding.etCustomtitle.text.toString(),
                                        description = dialogBinding.etCustomDescription.text.toString()
                                    )
                                )
                            return null
                        }

                        override fun onPostExecute(result: Void?) {
                            super.onPostExecute(result)
                            getNotesfun()
                        }

                    }
                    insert().execute()


                    /*   student.add(
                           NotesEntityDataClass(
                             title = dialogBinding.etCustomtitle.text.toString(),
                               description = dialogBinding.etCustomDescription.text.toString()
                           )
                       )*/

                    adapter.notifyDataSetChanged()
                    dialog.dismiss()

                }
            }
            dialog.show()
        }
        getNotesfun()
    }


    fun getNotesfun() {
        student.clear()

        class getNotesClass : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                student.addAll(notesDbObj1.notesDao().getNotes())
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                adapter.notifyDataSetChanged()
            }

        }
        getNotesClass().execute()
    }


    override fun onDeleteClick(position: Int) {
        class getdeleteNotesClass : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                notesDbObj1.notesDao().deleteNotes(student[position])
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                getNotesfun()

            }

        }
        getdeleteNotesClass().execute()
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
            if (dialogBinding.etCustomtitle.text.toString().isNullOrEmpty()) {
                dialogBinding.etCustomtitle.error = "Enter Your Name"
            } else if (dialogBinding.etCustomDescription.text.toString().isNullOrEmpty()) {
                dialogBinding.etCustomDescription.error = "Enter Your Rollno"
            } else {
                //                student.add(
//                    NotesEntityDataClass(
//                        title = dialogBinding.etCustomtitle.text.toString(),
//                        description = dialogBinding.etCustomDescription.text.toString()
//                    )
//                )
                class getupdateNotesClass : AsyncTask<Void, Void, Void>() {
                    override fun doInBackground(vararg p0: Void?): Void? {
                        notesDbObj1.notesDao().updateNotes(
                            NotesEntityDataClass(
                                id = student[position].id, // give the position's id
                                title = dialogBinding.etCustomtitle.text.toString(),
                                description = dialogBinding.etCustomDescription.text.toString()
                            )
                        )

                        return null
                    }

                    override fun onPostExecute(result: Void?) {
                        super.onPostExecute(result)
                        getNotesfun()
                    }


                }
                getupdateNotesClass().execute()

                dialog.dismiss()

            }
        }
        dialog.show()
    }
}







