package com.mamudo.challengetheheroes.api.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Thumbnail(var path: String, var extension: String) : Parcelable {
    val uri: String get() = "$path.$extension"
}