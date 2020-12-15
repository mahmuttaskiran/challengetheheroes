package com.mamudo.challengetheheroes.api

import com.mamudo.challengetheheroes.api.data.Character
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponseData(val results: List<Character>)

@JsonClass(generateAdapter = true)
data class MarvelResponse(val data: MarvelResponseData)