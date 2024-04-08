package com.example.movieapiaplicattion.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapiaplicattion.components.MainImage
import com.example.movieapiaplicattion.components.MainTopBar
import com.example.movieapiaplicattion.ui.theme.Title
import com.example.movieapiaplicattion.ui.theme.Topbar
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun DetailView(viewModel: PokemonViewModel, navController: NavController, name: String) {
    Scaffold(topBar = {
        MainTopBar(title = "Detalle del pokemon $name",true) {
            navController.popBackStack()
        }
    }) {
         ContentDeailView( viewModel,  it,  navController,name)
    }
}

@Composable
fun ContentDeailView(
    viewModel: PokemonViewModel,
    paddingValues: PaddingValues,
    navController: NavController,
    name: String
) {
    LaunchedEffect(Unit) {
        viewModel.fetchDetailPokemon(name)
    }
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Topbar)
    ) {
        Card( shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .padding(10.dp)
                .shadow(40.dp)
                .height(150.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column {
                MainImage(image = viewModel.state.front_default)
            }

        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.state.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.state.weight.toString())
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.state.order.toString())
    }

}
