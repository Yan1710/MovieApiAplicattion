package com.example.movieapiaplicattion.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapiaplicattion.viewmodel.PokemonViewModel

@Composable
fun HomeView(viewModel: PokemonViewModel){
    val pokemon by viewModel.pokemon.collectAsState()
    val pokemon1 by viewModel.pokemon1.collectAsState()
    LazyColumn{
        items(pokemon){ item ->
            Text(text = item.name)
        }

        if(pokemon1.isNotEmpty()){
            val urls = pokemon1.split(",").filter { it.isNotEmpty() }
            urls.forEach { url ->
                item {
                    LoadImageFromUrl(url)
                }
            }
        }

    }

}

@Composable
fun LoadImageFromUrl(pokemon1: String) {
    val painter: Painter = rememberImagePainter(
        data = pokemon1,
        builder = {
            // Opcional: puedes agregar transformaciones de imagen aquí, como recortar en círculo
            transformations(CircleCropTransformation())
        }
    )

    Image(
        painter = painter,
        contentDescription = "Image from URL",
        // Opcional: especifica un tamaño para la imagen
        // modifier = Modifier.size(100.dp)
    )
}
