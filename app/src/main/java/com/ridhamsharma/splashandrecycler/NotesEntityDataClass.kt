package com.ridhamsharma.splashandrecycler

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class NotesEntityDataClass(
    //Primary key :-unique id and will work on the column
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo
    var description: String?= null,
    @ColumnInfo
    var tile: String?= null

)



