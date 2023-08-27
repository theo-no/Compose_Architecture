package com.example.composearchitecture.service

import com.example.composearchitecture.model.response.PoketmonResponse
import com.example.composearchitecture.model.response.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PoketmonService {
    @GET("pokemon/")
    suspend fun getPokemons(): Response

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response

    @GET("pokemon/{pid}/")
    suspend fun getPokemon(@Path("pid") pid: Int): PoketmonResponse
}