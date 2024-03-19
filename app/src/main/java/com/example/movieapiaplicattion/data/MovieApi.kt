package com.example.movieapiaplicattion.data

import com.example.movieapiaplicattion.model.PokemonModel
import com.example.movieapiaplicattion.utils.Contants.Companion.LIST_POKEMON
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {   

    @GET(LIST_POKEMON)
    suspend fun  getPokemon() : Response<PokemonModel>

}