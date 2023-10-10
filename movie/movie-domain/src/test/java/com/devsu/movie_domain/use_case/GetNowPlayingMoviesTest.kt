package com.devsu.movie_domain.use_case

import com.devsu.core.Constants
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.movie_domain.FakeMovieRepository
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.repository.MovieRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNowPlayingMoviesTest {
    private lateinit var repository: MovieRepository
    private lateinit var getNowPlayingMovies: GetNowPlayingMovies

    @Before
    fun setUp(){
        repository = FakeMovieRepository()
        getNowPlayingMovies = GetNowPlayingMovies(
            repository = repository
        )
    }

    @Test
    fun getMovieList_WhenNetworkIsAvailable_responseListMovies() = runBlocking{
        val moviesAsFlow = getNowPlayingMovies.execute(
            language = Constants.DEFAULT_LANGUAGE,
            page = 1,
            isNetworkAvailable = true
        ).toList()

        //First emission
        assert(moviesAsFlow[0] is DataState.Loading)
        assert((moviesAsFlow[0] as DataState.Loading).progressBarState == ProgressBarState.Loading)

        //Second emission
        assert(moviesAsFlow[1] is DataState.Data<List<Movie>>)
        assert((moviesAsFlow[1] as DataState.Data<List<Movie>>).data?.isNotEmpty() ?: false)

        //Third emission
        assert(moviesAsFlow[2] is DataState.Loading)
        assert((moviesAsFlow[2] as DataState.Loading).progressBarState == ProgressBarState.Idle)
    }

    @Test
    fun getMovieList_WhenNetworkIsAvailable_responseError() = runBlocking{
        val moviesAsFlow = getNowPlayingMovies.execute(
            language = Constants.DEFAULT_LANGUAGE,
            page = 1,
            isNetworkAvailable = false
        ).toList()

        //First emission
        assert(moviesAsFlow[0] is DataState.Loading)
        assert((moviesAsFlow[0] as DataState.Loading).progressBarState == ProgressBarState.Loading)

        //Second emission
        assert(moviesAsFlow[1] is DataState.Response)
        assert(((moviesAsFlow[1] as DataState.Response).uiComponent as UIComponent.None).message == "Check you connection")

        //Third emission
        assert(moviesAsFlow[2] is DataState.Loading)
        assert((moviesAsFlow[2] as DataState.Loading).progressBarState == ProgressBarState.Idle)
    }
}