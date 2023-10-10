package com.devsu.movie_data.remote

import com.devsu.core.Constants
import com.devsu.movie_data.MockWebServerResponses
import com.google.gson.GsonBuilder
import com.google.gson.stream.MalformedJsonException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection
import kotlin.test.assertFailsWith

class MovieApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApi: MovieApi
    private lateinit var baseUrl: HttpUrl

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        baseUrl = mockWebServer.url("/")
        movieApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieApi::class.java)
    }

    @Test
    fun getSuccessMovieList() = runBlocking{
        val responseJson = MockWebServerResponses.nowPlayingMoviesResponseSuccess
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(responseJson)
        )

        val response = movieApi.getNowPlayingMovies(
            language = Constants.DEFAULT_LANGUAGE,
            page = 1,
        )

        assert(response.results?.isNotEmpty() == true)

        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assert(recordedRequest.path?.startsWith("/movie/now_playing") ?: false)
        //assertEquals("/movie/now_playing", recordedRequest.path)
    }

    @Test
    fun getWrongParseResponseMovieList() = runBlocking{
        val responseJson = MockWebServerResponses.nowPlayingMoviesResponseMalformedJson
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(responseJson)
        )

        assertFailsWith<MalformedJsonException>{
            movieApi.getNowPlayingMovies(
                language = Constants.DEFAULT_LANGUAGE,
                page = 1,
            )
        }


        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assert(recordedRequest.path?.startsWith("/movie/now_playing") ?: false)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}