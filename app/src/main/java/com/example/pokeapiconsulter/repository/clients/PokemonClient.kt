package com.example.pokeapiconsulter.repository.clients

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(PokemonApiService::class.java)
}