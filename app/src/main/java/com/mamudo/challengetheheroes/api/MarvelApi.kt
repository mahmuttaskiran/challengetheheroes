package com.mamudo.challengetheheroes.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<MarvelResponse>

    @GET("/v1/public/characters/{id}")
    fun getCharacter(@Path("id") id: Int): Call<MarvelResponse>
}