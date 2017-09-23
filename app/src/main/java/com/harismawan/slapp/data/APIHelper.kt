package com.harismawan.slapp.data

import retrofit2.Call
import retrofit2.http.GET

interface APIHelper {

    @GET("kosakata.json")
    fun getAll(): Call<List<Word>>
}