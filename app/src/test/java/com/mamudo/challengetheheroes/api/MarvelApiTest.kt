package com.mamudo.challengetheheroes.api

import com.mamudo.challengetheheroes.di.DaggerTestApplicationGraph
import com.mamudo.challengetheheroes.utils.ResourceReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection
import javax.inject.Inject


class MarvelApiTest {
    lateinit var mockWebServer: MockWebServer
    @Inject
    lateinit var api: MarvelApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        DaggerTestApplicationGraph.create().inject(this)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should get characters`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResourceReader("get_character_success.json").read())
        mockWebServer.enqueue(response)
        val actualResponse = api.getCharacters(0, 1)
        val firstCharacter = actualResponse.blockingStream().findFirst().get().data.results.first()
        assertNotNull(firstCharacter)
    }
}