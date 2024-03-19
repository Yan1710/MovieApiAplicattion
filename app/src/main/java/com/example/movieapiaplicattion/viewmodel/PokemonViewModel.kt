package com.example.movieapiaplicattion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapiaplicattion.model.PokemonList
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

    val pokemon = _poke.asStateFlow()


    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getPokemon()
                _poke.value = result ?: emptyList()
            }
        }
    }

}