package com.example.movieapiaplicattion.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapiaplicattion.R
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun HomeView(viewModel: PokemonViewModel) {
    val pokemon by viewModel.pokemon.collectAsState()
    val pokemon1 by viewModel.pokemon1.collectAsState()
    
    LazyColumn {
        items(pokemon) { item ->
            Text(text = item.name)
        }

        if (pokemon1.isNotEmpty()) {
            val urls = pokemon1.split(",").filter { it.isNotEmpty() }
            urls.forEach { url ->
                item {
                    LoadImageFromUrl(url)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }

}

@Composable
fun LoadImageFromUrl(pokemon1: String) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = pokemon1).apply(block = fun ImageRequest.Builder.() {
            error(R.drawable.images)
        }).size(1000,1000)
            .build()

    )
    Image(
        painter = painter,
        contentDescription = "Image from URL",
        modifier = Modifier.height(150.dp)
    )
}
