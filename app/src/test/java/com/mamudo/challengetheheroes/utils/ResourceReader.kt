package com.mamudo.challengetheheroes.utils

import java.io.InputStreamReader

class ResourceReader(private val path: String) {
    fun read(): String {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        return reader.readText()
    }
}

