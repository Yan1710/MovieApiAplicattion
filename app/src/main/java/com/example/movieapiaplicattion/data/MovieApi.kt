package com.example.movieapiaplicattion.data

import com.example.movieapiaplicattion.model.DetailPokemonsModel
import com.example.movieapiaplicattion.model.Pokemon
import com.example.movieapiaplicattion.model.PokemonList
import com.example.movieapiaplicattion.model.PokemonModel
import com.example.movieapiaplicattion.utils.Contants.Companion.LIST_POKEMON
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {   

    @GET(LIST_POKEMON)
    suspend fun  getPokemons() : Response<PokemonModel>
    @GET("pokemon/{name}")
    suspend fun  getPokemon(@Path("name")name:String): Response<Pokemon>
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name")name:String):Response<DetailPokemonsModel>

}