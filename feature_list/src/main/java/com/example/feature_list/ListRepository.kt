package com.example.feature_list

import com.example.api.CharactersApi
import com.example.api.Results
import io.reactivex.Single
import javax.inject.Inject

interface ListRepository {
    fun fetchCharacters(): Single<Results>
}

class ListRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi) :
    ListRepository {

    override fun fetchCharacters(): Single<Results> = charactersApi.getCharacters()

}