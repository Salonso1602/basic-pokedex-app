package com.example.pokeapiconsulter.repository.clients

import com.example.pokeapiconsulter.repository.clients.responses.FetchAllPokemonResponse
import com.example.pokeapiconsulter.repository.clients.responses.PokemonDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun fetchAllPokemon(@Query("offset") offset: Int ): FetchAllPokemonResponse

    @GET("pokemon/{pokemonId}")
    suspend fun fetchPokemon(@Path("pokemonId") pokemonId: String): PokemonDetailsResponse
}
