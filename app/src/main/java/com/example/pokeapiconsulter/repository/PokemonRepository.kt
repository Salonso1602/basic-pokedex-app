package com.example.pokeapiconsulter.repository

import com.example.pokeapiconsulter.repository.clients.PokemonClient
import com.example.pokeapiconsulter.repository.clients.responses.PokemonDetailsResponse
import com.example.pokeapiconsulter.repository.clients.responses.PokemonListItemResponse

object PokemonRepository {

    suspend fun fetchPokemons(offset: Int = 0): List<PokemonListItemResponse> {
        return PokemonClient.service.fetchAllPokemon(offset).results
    }

    suspend fun fetchPokemonDetails(name: String): PokemonDetailsResponse {
        return PokemonClient.service.fetchPokemon(name)
    }
}