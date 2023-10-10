package com.devsu.movie_ui_tv.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.ErrorSupportFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devsu.movie_ui_tv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment: ErrorSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setErrorContent()
    }

    private fun setErrorContent() {
        imageDrawable =
            ContextCompat.getDrawable(requireActivity(), androidx.leanback.R.drawable.lb_ic_sad_cloud)

        setDefaultBackground(TRANSLUCENT)

        title = getString(com.devsu.core_ui.R.string.title_movie_list)
        buttonText = resources.getString(R.string.dismiss_error)
        buttonClickListener = View.OnClickListener {
            Log.v("ErrorFragment", "dismiss click")
            findNavController().popBackStack()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("ErrorFragment", "onViewCreated")
        setParams()
    }

    private fun setParams(){
        val messageError = arguments?.getString("message")
        message = messageError
    }

    companion object {
        private val TRANSLUCENT = true
    }
}