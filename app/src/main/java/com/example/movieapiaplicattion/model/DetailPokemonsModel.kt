package com.example.movieapiaplicattion.model

data class DetailPokemonsModel(
    val name: String,
    val order: Int,
    val weight: Int,
    val sprites: sprites
)

data class sprites(
    val front_default: String
)
