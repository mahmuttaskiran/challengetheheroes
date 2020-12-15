package com.mamudo.challengetheheroes.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(var path: String, var extension: String)