package com.example.movieapiaplicattion.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapiaplicattion.model.ListPokemon
import com.example.movieapiaplicattion.model.PokemonList
import com.example.movieapiaplicattion.repository.PokeRepository
import com.example.movieapiaplicattion.state.DetailPokemonState
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
    private val _poke1 = MutableStateFlow<List<ListPokemon>>(emptyList())

    val pokemon1 = _poke1.asStateFlow()
    var state by mutableStateOf(DetailPokemonState())
        private set

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
                if (pokemonList.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        val pokemonWithNameAndUrlList: MutableList<ListPokemon> = mutableListOf()
                        for (pokemon in pokemonList) {
                            val result = repository.getPokemons(pokemon.name)
                            result?.let { listSprites ->
                                if (!listSprites.front_default.isNullOrEmpty()) {
                                    val allSpritesUrls =
                                        ListPokemon(pokemon.name, result.front_default)
                                    pokemonWithNameAndUrlList.add(allSpritesUrls)
                                }
                            }
                        }
                        _poke1.value = pokemonWithNameAndUrlList
                    }
                }
            }
        }
    }

    fun fetchDetailPokemon(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getPokemonsDetail(name)
                state = state.copy(
                    name = result?.name ?: "",
                    order = result?.order ?: 0,
                    weight = result?.weight ?: 0,
                )
            }
        }
    }


}