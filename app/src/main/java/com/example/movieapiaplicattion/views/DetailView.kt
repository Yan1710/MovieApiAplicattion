package com.example.movieapiaplicattion.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.movieapiaplicattion.components.MainTopBar
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun DetailView(viewModel: PokemonViewModel, navController: NavController, name: String) {
    Scaffold(topBar = {
        MainTopBar(title = "LISTA DE POKEMONES",true) {
            navController.popBackStack()
        }
    }) {
         ContentDeailView( viewModel,  it,  navController,name)
    }
}
@Composable
fun ContentDeailView(viewModel: PokemonViewModel, paddingValues: PaddingValues, navController: NavController, name: String){
    val pokemon1 by viewModel.pokemon1.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchDetailPokemon(name)
    }

}
