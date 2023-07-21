package com.ridhamsharma.splashandrecycler

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDaoInterface {

    @Insert
    fun insertNotes(notesEntityDataClass: NotesEntityDataClass)

    @Query("SELECT * FROM NotesEntityDataClass")
    fun getNotes(notesDb: NotesDb)
}