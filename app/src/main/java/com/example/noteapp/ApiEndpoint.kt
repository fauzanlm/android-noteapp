package com.example.noteapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("notes")
    fun data() : Call<List<NoteModel>>
}