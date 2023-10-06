package com.devsu.movie_ui_tv.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.devsu.movie_ui_tv.R

class SpinnerFragment: DialogFragment() {
    private val TAG = SpinnerFragment::class.java.simpleName

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(
            R.layout.spinner_dialog,
            container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        val window: Window? = dialog?.window
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}