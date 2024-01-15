package com.devsu.movie_ui_tv.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.VerticalGridPresenter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
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
    private val viewModel: MovieListViewModel by viewModels()

    private var mAdapter: ArrayObjectAdapter? = null

    private lateinit var mBackgroundManager: BackgroundManager
    private lateinit var mMetrics: DisplayMetrics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setParams()
        setupEventListeners()
        prepareBackgroundManager()

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
                    Toast.makeText(requireContext(), getString(R.string.clicking_movie, item.title), Toast.LENGTH_SHORT).show()
                }
            }


        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Movie) {
                //Change background?
                updateBackground(item.photoUrl)
            }
        }
    }

    private fun updateBackground(url: String){
        val request = ImageRequest.Builder(requireContext())
            .data(url)
            .crossfade(true)
            .target { drawable ->
                // Handle the result.
                drawable.alpha = (255 * 0.3).toInt()
                mBackgroundManager.drawable = drawable
            }
            .build()
        val imageLoader = ImageLoader(requireContext())
        imageLoader.enqueue(request)
    }

    private fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(requireActivity())
        mBackgroundManager.attach(requireActivity().window)
        //mDefaultBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.default_background)
        mMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    private fun showError(message: String) {
        val bundle = Bundle().apply {
            putString("message", message)
        }
        findNavController().navigate(R.id.errorFragment, bundle)
        //findNavController().navigate(R.id.action_movieListFragment_to_errorFragment, bundle)
    }
}