package com.devsu.movie_ui_tv.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.VerticalGridPresenter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_ui_tv.MovieListUiEvent
import com.devsu.movie_ui_tv.MovieListViewModel
import com.devsu.movie_ui_tv.R
import com.devsu.movie_ui_tv.components.MoviePresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment: VerticalGridSupportFragment() {
    private val viewModel: MovieListViewModel by activityViewModels()

    private var mAdapter: ArrayObjectAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setParams()
        setupEventListeners()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupFragment() {

        val gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = viewModel.numColumns
        gridPresenter.shadowEnabled = true
        setGridPresenter(gridPresenter)

        setupAdapterMovies()
    }

    private fun setupAdapterMovies(){
        mAdapter = ArrayObjectAdapter(
            MoviePresenter(viewModel.cardWidth, viewModel.cardHeight))
        adapter = mAdapter
    }

    private fun setParams(){
        title = getString(com.devsu.core_ui.R.string.title_movie_list)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.uiEvent.collect{ uiEvent->
                    when(uiEvent){
                        is MovieListUiEvent.ShowError -> {
                            showError(uiEvent.message)
                        }
                        MovieListUiEvent.SuccessSearch -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.movies.collect{ movies->
                    mAdapter = null
                    setupAdapterMovies()
                    movies.forEach {
                        mAdapter?.add(it)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.progressBarState.collect{ progressBarState->
                    when(progressBarState){
                        ProgressBarState.Idle -> {
                            findNavController().popBackStack(destinationId = R.id.spinnerFragment, inclusive = true)
                        }
                        ProgressBarState.Loading -> {
                            findNavController().navigate(R.id.spinnerFragment)
                        }
                    }
                }
            }
        }
    }

    private fun setupEventListeners() {

        onItemViewClickedListener =
            OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
                if (item is Movie) {
                    //redirect to detail
                }
            }


        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Movie) {
                //Change background?
            }
        }
    }

    private fun showError(message: String) {
        val bundle = Bundle().apply {
            putString("message", message)
        }
        findNavController().navigate(R.id.errorFragment, bundle)
        //findNavController().navigate(R.id.action_movieListFragment_to_errorFragment, bundle)
    }
}