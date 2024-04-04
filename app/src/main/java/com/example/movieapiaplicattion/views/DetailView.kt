package com.example.movieapiaplicattion.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun DetailView(viewModel: PokemonViewModel,navController: NavController,name:String){
Text(text = name)
}
