package com.mamudo.challengetheheroes.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelUrl(val resourceURI: String, val name: String)
