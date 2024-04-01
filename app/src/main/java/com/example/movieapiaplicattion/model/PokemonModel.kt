package com.example.movieapiaplicattion.model

data class PokemonModel(
    val count:Int,
    val results : List<PokemonList>
)
data class PokemonList(
    val name:String,
    val url:String
)

data class Pokemon(
    val sprites:listSprites
)
data class listSprites(
    val front_default:String
)