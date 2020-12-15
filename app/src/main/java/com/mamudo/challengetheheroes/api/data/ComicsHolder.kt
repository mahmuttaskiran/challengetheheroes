package com.mamudo.challengetheheroes.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsHolder(
    val available: Int,
    val items: List<MarvelUrl>
)