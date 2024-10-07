package app.ditodev.notes.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.ditodev.notes.data.db.entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update (note : Note)

    @Delete
    fun delete(note : Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes():LiveData<List<Note>>


}