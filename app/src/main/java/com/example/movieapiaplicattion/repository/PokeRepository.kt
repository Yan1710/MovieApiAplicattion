package com.example.movieapiaplicattion.repository

import com.example.movieapiaplicattion.data.MovieApi
import com.example.movieapiaplicattion.model.Pokemon
import com.example.movieapiaplicattion.model.PokemonList
import com.example.movieapiaplicattion.model.listSprites
import javax.inject.Inject

class PokeRepository @Inject constructor(private val apiPokemon:MovieApi) {

    suspend fun getPokemon(): List<PokemonList>?{
        val response = apiPokemon.getPokemons()
        if(response.isSuccessful){
            return response.body()?.results
        }
        return null
    }
    suspend fun getPokemons(name:String): listSprites? {
        val response = apiPokemon.getPokemon(name)
        if(response.isSuccessful){
            return response.body()?.sprites
        }
        return null
    }
}