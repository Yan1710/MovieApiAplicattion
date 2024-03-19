package com.example.movieapiaplicattion.repository

import com.example.movieapiaplicattion.data.MovieApi
import com.example.movieapiaplicattion.model.PokemonList
import javax.inject.Inject

class PokeRepository @Inject constructor(private val apiPokemon:MovieApi) {

    suspend fun getPokemon(): List<PokemonList>?{
        val response = apiPokemon.getPokemon()
        if(response.isSuccessful){
            return response.body()?.results
        }
        return null
    }

}