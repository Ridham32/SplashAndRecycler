package com.ridhamsharma.splashandrecycler

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDaoInterface {

    @Insert
    fun insertNotes(notesEntityDataClass: NotesEntityDataClass)

    @Query("SELECT * FROM NotesEntityDataClass")
    fun getNotes(): List<NotesEntityDataClass>

    @Delete
    fun deleteNotes(notesEntityDataClass: NotesEntityDataClass)

    @Update
    fun updateNotes(notesEntityDataClass: NotesEntityDataClass)
}