package app.ditodev.notes.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import app.ditodev.notes.data.db.NoteRoomDatabase
import app.ditodev.notes.data.db.dao.NoteDao
import app.ditodev.notes.data.db.entity.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val notedDao: NoteDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        notedDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = notedDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { notedDao.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { notedDao.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute {
            notedDao.update(note)
        }
    }
}