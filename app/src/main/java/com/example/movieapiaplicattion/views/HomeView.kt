package com.example.movieapiaplicattion.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun HomeView(viewModel: PokemonViewModel){
    val pokemon by viewModel.pokemon.collectAsState()
    LazyColumn{
        items(pokemon){ item ->
            Text(text = item.name)
        }
    }
}