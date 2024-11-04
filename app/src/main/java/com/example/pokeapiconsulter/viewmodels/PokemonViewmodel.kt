package com.example.pokeapiconsulter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapiconsulter.repository.PokemonRepository
import com.example.pokeapiconsulter.repository.clients.responses.PokemonDetailsResponse
import com.example.pokeapiconsulter.repository.clients.responses.PokemonListItemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel(
    val navigateToDetails: (String) -> Unit = {},
    val navigateToMainMenu: () -> Unit = {},
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonScreenUiState())
    val uiState = _uiState.asStateFlow()


    init {
        fetchPokemons()
    }

    fun fetchPokemons() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loadingMorePokemon = true)
            }
            val pokemons = PokemonRepository.fetchPokemons(
                _uiState.value.pokemonList.size
            )
            _uiState.update {
                it.copy(pokemonList = it.pokemonList.plus(pokemons), loadingMorePokemon = false)
            }
        }
    }

    fun fetchPokemonDetails(name: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(loadingDetails = true)
            }
            val pokemonDetails = PokemonRepository.fetchPokemonDetails(name)
            _uiState.update {
                it.copy(pokemonDetails = pokemonDetails, loadingDetails = false)
            }
        }
    }

    fun onNavigateToDetails(pokemonId: String){
        navigateToDetails(pokemonId)
    }
    fun onNavigateToMainMenu(){
        navigateToMainMenu()
    }
}

data class PokemonScreenUiState(
    val pokemonList: List<PokemonListItemResponse> = emptyList(),
    val pokemonDetails: PokemonDetailsResponse? = null,
    val loadingDetails: Boolean = true,
    val loadingMorePokemon: Boolean = true,
)
