package com.example.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("character/")
    fun getCharacters(): Single<Results>
}