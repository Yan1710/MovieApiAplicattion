package com.example.movieapiaplicattion.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapiaplicattion.components.CardPokemon
import com.example.movieapiaplicattion.components.MainTopBar
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun HomeView(viewModel: PokemonViewModel) {
    Scaffold(
        topBar = {
            MainTopBar(title = "LISTA DE POKEMONES") {
            }
        }
    ) {
        ContentHomeView(viewModel = viewModel, it)
    }

}

@Composable
fun ContentHomeView(viewModel: PokemonViewModel, paddingValues: PaddingValues) {
    val pokemon by viewModel.pokemon.collectAsState()
    val pokemon1 by viewModel.pokemon1.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color.Black)
    ) {
        items(pokemon) { item ->
            if (pokemon1.isNotEmpty()) {
                val urls = pokemon1.split(",").filter { it.isNotEmpty() }
                urls.forEach { url ->
                    CardPokemon(pokemonList = item, pokeimage = url) {
                    }
                }
            }
            Text(text = item.name)
        }
    }
}


