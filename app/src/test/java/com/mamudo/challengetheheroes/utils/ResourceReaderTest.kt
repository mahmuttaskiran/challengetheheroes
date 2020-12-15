package com.mamudo.challengetheheroes.utils

import junit.framework.TestCase.assertNotNull
import org.junit.Test

class ResourceReaderTest {
    @Test
    fun `read content from resource file`() {
        assertNotNull(ResourceReader("get_character_failed.json").read())
    }
}