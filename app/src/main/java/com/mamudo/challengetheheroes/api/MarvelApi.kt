package com.mamudo.challengetheheroes.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<MarvelResponse>

    @GET("/v1/public/characters/{id}")
    fun getCharacter(@Path("id") id: Int): Observable<MarvelResponse>

    @GET("/v1/public/characters/{id}/comics")
    fun getCharacterComics(
        @Path("id") id: Int,
        @Query("orderBy") orderBy: String = "-onsaleDate",
        @Query("limit") limit: Int = 10
    ): Observable<MarvelResponse>
}