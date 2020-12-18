package com.mamudo.challengetheheroes.api.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ComicsHolder(
    val available: Int,
    val items: List<MarvelUrl>
) : Parcelable