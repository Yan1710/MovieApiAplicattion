package com.example.movieapiaplicattion.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapiaplicattion.components.CardPokemon
import com.example.movieapiaplicattion.components.MainTopBar
import com.example.movieapiaplicattion.components.ShowLoading
import com.example.movieapiaplicattion.ui.theme.ColorFondo
import com.example.movieapiaplicattion.ui.theme.Topbar
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun HomeView(viewModel: PokemonViewModel,navController: NavController) {
    Scaffold(
        topBar = {
            MainTopBar(title = "LISTA DE POKEMONES") {
            }
        }
    ) {
        ContentHomeView(viewModel = viewModel, it,navController)
    }

}

@Composable
fun ContentHomeView(viewModel: PokemonViewModel, paddingValues: PaddingValues,navController: NavController) {
    val pokemon1 by viewModel.pokemon1.collectAsState()
    if (pokemon1.isNotEmpty()) {
        ShowLoading(isLoading = false)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Topbar)
        ) {
            items(pokemon1) { item ->
                CardPokemon(pokemonList = item) {
                    Log.d("CONtentclick", "ContentHomeView: ")
                    navController.navigate("DetailView/${item.name}")
                }
            }

        }
    } else {
        ShowLoading(isLoading = true)
    }
}



