package com.devsu.movie_ui_mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_ui_mobile.components.MovieListItem
import com.devsu.movie_ui_mobile.theme.Typography

@OptIn(ExperimentalComposeApi::class)
@Composable
fun MovieListScreen(
    scaffoldState: SnackbarHostState,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is MovieListUiEvent.ShowError -> {
                    scaffoldState.showSnackbar(event.message)
                }
                MovieListUiEvent.SuccessSearch -> Unit
            }
        }
    }

    Column {
        Text(
            text = stringResource(id = com.devsu.core_ui.R.string.title_movie_list),
            modifier = Modifier.padding(20.dp),
            style = Typography.headlineMedium
        )

        if(state.movies.isNotEmpty()){
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2)
            ){
                items(state.movies){ movie ->
                    MovieListItem(
                        movie = movie,
                        modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(state.progressBarState == ProgressBarState.Loading){
            CircularProgressIndicator()
        }
    }

}