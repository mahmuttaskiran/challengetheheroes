package com.mamudo.challengetheheroes

import com.mamudo.challengetheheroes.api.data.MarvelUrl
import com.mamudo.challengetheheroes.utils.MarvelUrlComparator
import org.junit.Test

class ComparatorTest {
    @Test
    fun test1() {
        val list = listOf(
            MarvelUrl("", "hello (2010)"),
            MarvelUrl("", "(2001) hello "),
            MarvelUrl("", "hel(2020)lo "),
        )
        assert(list.sortedWith(MarvelUrlComparator()) == listOf(
            MarvelUrl("", "hel(2020)lo "),
            MarvelUrl("", "hello (2010)"),
            MarvelUrl("", "(2001) hello "),
        ))
    }
}



