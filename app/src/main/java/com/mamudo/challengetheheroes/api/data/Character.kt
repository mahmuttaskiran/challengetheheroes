package com.mamudo.challengetheheroes.api.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail,
    val comics: ComicsHolder?
) : Parcelable