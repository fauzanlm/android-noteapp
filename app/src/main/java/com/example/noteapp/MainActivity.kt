package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api.data().enqueue(object : Callback<List<NoteModel>> {
            override fun onResponse(p0: Call<List<NoteModel>>, p1: Response<List<NoteModel>>) {
                if (p1.isSuccessful) {
                    val listData = p1.body()!!
                    listData.forEach {
                        Log.e("Note : ", it.note.toString());
                    }
                }
            }

            override fun onFailure(p0: Call<List<NoteModel>>, p1: Throwable) {
                Log.e("MainActivity : ", p1.toString());
            }

        })
    }
}