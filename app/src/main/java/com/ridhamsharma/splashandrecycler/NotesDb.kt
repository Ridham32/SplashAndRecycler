package com.ridhamsharma.splashandrecycler

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version=1,entities=[NotesEntityDataClass::class])
abstract class NotesDb:RoomDatabase() {
             //the interaction occur through Dao
    abstract fun notesDao():NotesDaoInterface           //abstract fun must be in the abstract class

    companion object{
     var notesDb: NotesDb?= null
     //it will return the instance of the class
      fun getNotesDatabase(context: Context):NotesDb{
       if(notesDb==null){
                                                                  //to give name to the app
        notesDb= Room.databaseBuilder(context,NotesDb::class.java,context.resources.getString(R.string.app_name)).build()

       }
      return notesDb!!


      }
    }
}