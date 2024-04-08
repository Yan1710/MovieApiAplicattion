package com.example.movieapiaplicattion.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel
import com.example.movieapiaplicattion.views.DetailView
import com.example.movieapiaplicattion.views.HomeView
import com.example.movieapiaplicattion.views.SearchPokeView


@Composable
fun NavManager(viewModel: PokemonViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(viewModel, navController)
        }
        composable("DetailView/{name}", arguments = listOf(
            navArgument("name"){type = NavType.StringType}
        )){
            val name = it.arguments?.getString("name") ?: " "
            DetailView(viewModel, navController,name)
        }
        composable("SearchPokeView"){
            SearchPokeView(viewModel, navController )
        }
    }
}