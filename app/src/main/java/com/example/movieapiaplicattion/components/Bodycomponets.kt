package com.example.movieapiaplicattion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.movieapiaplicattion.R
import com.example.movieapiaplicattion.model.ListPokemon
import com.example.movieapiaplicattion.model.PokemonList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, showBackButton: Boolean = false, onClickAnction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFFC55810)
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onClickAnction }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "BACK",
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@Composable
fun CardPokemon(pokemonList: ListPokemon, onClickAnction: () -> Unit) {
    val pressed = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(10.dp)
            .shadow(40.dp)
            .clickable { onClickAnction }

    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(10.dp)
                .shadow(40.dp)
        ) {
            Column {
                MainImage(image = pokemonList.url)
            }
        }
        Row(modifier = Modifier.padding(9.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text(text = pokemonList.name, color = Color.Black,
                modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    pressed.value = !pressed.value
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (!pressed.value) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    tint = Color.Red,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun MainImage(image: String) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = image)
            .apply(block = fun ImageRequest.Builder.() {
                error(R.drawable.images)
                scale(Scale.FILL)
            }).scale(Scale.FILL)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = "Image from URL",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}