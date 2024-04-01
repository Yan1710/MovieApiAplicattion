package com.example.movieapiaplicattion.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapiaplicattion.model.Pokemon
import com.example.movieapiaplicattion.model.PokemonList
import com.example.movieapiaplicattion.model.listSprites
import com.example.movieapiaplicattion.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokeRepository) : ViewModel() {

    private val _poke = MutableStateFlow<List<PokemonList>>(emptyList())
    private val _poke1 = MutableStateFlow<String>("")

    val pokemon = _poke.asStateFlow()
    val pokemon1 = _poke1.asStateFlow()

    init {
        fetchPokemon()
        fetchpokemons()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getPokemon()
                _poke.value = result ?: emptyList()
            }
        }
    }

    private fun fetchpokemons() {
        viewModelScope.launch {
            _poke.collect { pokemonList ->
                // Este bloque se ejecutará una vez que _poke tenga un valor
                withContext(Dispatchers.IO) {
                    val allSpritesUrls = mutableListOf<String>()
                    for (pokemon in pokemonList) {
                        val result = repository.getPokemons(pokemon.name)
                        // Manejar result
                        result?.let { listSprites ->
                          if(!listSprites.equals("")){
                              val firstSprite = listSprites.front_default
                             allSpritesUrls.add(firstSprite)
                          }
                        }
                    }
                    val concatenatedUrls = allSpritesUrls.joinToString(separator = ",")
                    _poke1.value = concatenatedUrls
                }
            }
        }
    }



}