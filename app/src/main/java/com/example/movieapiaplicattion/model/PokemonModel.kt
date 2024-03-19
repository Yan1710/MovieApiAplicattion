package com.example.movieapiaplicattion.model

data class PokemonModel(
    val count:Int,
    val results : List<PokemonList>
)
data class PokemonList(
    val name:String,
    val url:String
)
