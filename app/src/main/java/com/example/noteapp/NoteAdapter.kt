package com.example.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    val notes: ArrayList<NoteModel>
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater.from(p0.context)
            .inflate(R.layout.adaptor_note, p0, false)
    )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, index: Int) {
        val data = notes[index]
        holder.textNote.text = data.note
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textNote = view.findViewById<TextView>(R.id.text_note)
        val imageDelete = view.findViewById<ImageView>(R.id.image_delete)
    }

    public fun setData(data: List<NoteModel>) {
        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()
    }
}