package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var listNote : RecyclerView
    private lateinit var fabCreate : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpList()
        setUpListener()
    }
    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun setUpView(){
        fabCreate = findViewById(R.id.fab_create)
        listNote = findViewById(R.id.list_note)
    }

    private fun setUpList(){
        noteAdapter = NoteAdapter(arrayListOf())
        listNote.adapter = noteAdapter
    }

    private fun setUpListener(){
        fabCreate.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }
    private fun getNote(){
        api.data().enqueue(object : Callback<List<NoteModel>> {
            override fun onResponse(p0: Call<List<NoteModel>>, p1: Response<List<NoteModel>>) {
                if (p1.isSuccessful) {
                    val listData = p1.body()!!

                    noteAdapter.setData(listData)
//                    listData.forEach {
//                        Log.e("Note : ", it.note.toString());
//                    }

                }
            }

            override fun onFailure(p0: Call<List<NoteModel>>, p1: Throwable) {
                Log.e("MainActivity : ", p1.toString())
            }

        })
    }

}