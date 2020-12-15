package com.mamudo.challengetheheroes.utils

import com.mamudo.challengetheheroes.api.data.MarvelUrl
import java.util.Comparator

class MarvelUrlComparator: Comparator<MarvelUrl> {
    private fun String.getYear(): Int {
        val regex =  """\(([\d]{4})\)""".toRegex()
        val matchResult = regex.find(this)
        val values = matchResult?.groupValues ?: listOf()
        if (values.isEmpty()) return 0
        return values.first().substring(1, 5).toInt()
    }

    override fun compare(a: MarvelUrl, b: MarvelUrl) = b.name.getYear() - a.name.getYear()
}