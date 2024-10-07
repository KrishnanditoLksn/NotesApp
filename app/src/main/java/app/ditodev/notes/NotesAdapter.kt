package app.ditodev.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.ditodev.notes.data.db.entity.Note
import app.ditodev.notes.databinding.ItemNoteBinding

class NotesAdapter(private val onItemCallBack: OnItemCallBack) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var listNotes = ArrayList<Note>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
        }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNoteBinding.bind(view)
        fun bind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDescription.text = note.description
            binding.cvItemNote.setOnClickListener {
                onItemCallBack.onItemClicked(note, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    interface OnItemCallBack {
        fun onItemClicked(selectedNote: Note?, pos: Int)
    }
}