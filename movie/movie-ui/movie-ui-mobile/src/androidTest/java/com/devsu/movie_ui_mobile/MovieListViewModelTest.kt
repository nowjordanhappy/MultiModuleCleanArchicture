package com.devsu.movie_ui_mobile

import com.devsu.core.Constants
import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.core_ui.utils.ManagerConnection
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_domain.use_case.GetNowPlayingMovies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MovieListViewModelTest{
    //private var getNowPlayingMovies: GetNowPlayingMovies = mockk(relaxed = true)
    private var managerConnection: ManagerConnection = mockk(relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testOnGetMoviesSuccess_forConnectionAvailable(){
        val expectedMovies = listOf(
            Movie(
                id = 1,
                title = "Movie Title 1",
                originalTitle = "Original Title 1",
                overview = "Overview of Movie 1",
                photoUrl = "https://example.com/photo1.jpg",
                posterUrl = "https://example.com/poster1.jpg",
                releaseDate = "2023-10-01",
                voteAverage = 7.5,
                isForAdult = false
            ),
            Movie(
                id = 2,
                title = "Movie Title 2",
                originalTitle = "Original Title 2",
                overview = "Overview of Movie 2",
                photoUrl = "https://example.com/photo2.jpg",
                posterUrl = "https://example.com/poster2.jpg",
                releaseDate = "2023-10-02",
                voteAverage = 8.0,
                isForAdult = true
            ),
            Movie(
                id = 3,
                title = "Movie Title 3",
                originalTitle = "Original Title 3",
                overview = "Overview of Movie 3",
                photoUrl = "https://example.com/photo3.jpg",
                posterUrl = "https://example.com/poster3.jpg",
                releaseDate = "2023-10-03",
                voteAverage = 6.8,
                isForAdult = false
            )
        )
        val dataState: Flow<DataState<List<Movie>>> = flow {
            emit(DataState.Data(expectedMovies))
        }

        val getNowPlayingMovies: GetNowPlayingMovies = mockk(relaxed = true)
        every { managerConnection.isNetworkAvailable()} returns true
        every { getNowPlayingMovies.execute(language = Constants.DEFAULT_LANGUAGE, page = 1, isNetworkAvailable = true) } returns dataState

        val viewModel = MovieListViewModel(getNowPlayingMovies, managerConnection)


        assertTrue(viewModel.state.movies.isEmpty())
        assertEquals(ProgressBarState.Idle, viewModel.state.progressBarState)

        viewModel.onEvent(MovieListEvent.OnGetMovies)

        coVerify {
            getNowPlayingMovies.execute(language = Constants.DEFAULT_LANGUAGE, page = 1, isNetworkAvailable = true)
        }

        assertTrue(viewModel.state.movies.isNotEmpty())
        assertEquals(ProgressBarState.Idle, viewModel.state.progressBarState)
    }

    @Test
    fun testOnGetMoviesSuccess_forConnectionNotAvailable() = runBlocking{
        val dataState: Flow<DataState<List<Movie>>> = flow {
            emit(DataState.Loading(ProgressBarState.Loading))
            emit(DataState.Response(UIComponent.None("Check your connection")))
            emit(DataState.Loading(ProgressBarState.Idle))
        }

        val getNowPlayingMovies: GetNowPlayingMovies = mockk(relaxed = true)
        every { managerConnection.isNetworkAvailable()} returns false
        every { getNowPlayingMovies.execute(language = Constants.DEFAULT_LANGUAGE, page = 1, isNetworkAvailable = false) } returns dataState

        val viewModel = MovieListViewModel(getNowPlayingMovies, managerConnection)

        viewModel.onEvent(MovieListEvent.OnGetMovies)

        coVerify {
            getNowPlayingMovies.execute(language = Constants.DEFAULT_LANGUAGE, page = 1, isNetworkAvailable = any())
        }

        val result: List<DataState<List<Movie>>> = getNowPlayingMovies.execute(language = Constants.DEFAULT_LANGUAGE, page = 1, isNetworkAvailable = false).toList()

        assertEquals(result[0], DataState.Loading<ProgressBarState>(ProgressBarState.Loading))
        assertTrue(result[1] is DataState.Response)
        assertTrue((result[1] as DataState.Response).uiComponent is UIComponent.None)
        val uiComponent = (result[1] as DataState.Response).uiComponent as UIComponent.None
        assertEquals("Check your connection", uiComponent.message)
        assertEquals(result[2], DataState.Loading<ProgressBarState>(ProgressBarState.Idle))

        assertTrue(viewModel.state.movies.isEmpty())
        //assertEquals(ProgressBarState.Idle, viewModel.state.progressBarState)
    }
}