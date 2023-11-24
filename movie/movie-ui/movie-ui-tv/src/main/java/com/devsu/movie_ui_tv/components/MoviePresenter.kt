package com.devsu.movie_ui_tv.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import coil.load
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_ui_tv.R
import com.devsu.movie_ui_tv.databinding.CustomImageCardViewBinding
import kotlin.properties.Delegates

class MoviePresenter(
    private val width: Int,
    private val height: Int,
) : Presenter() {
    private var mDefaultCardImage: Drawable? = null
    private var sSelectedBackgroundColor: Int by Delegates.notNull()
    private var sDefaultBackgroundColor: Int by Delegates.notNull()

    private var defaultDateString = "No Date"


    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        sDefaultBackgroundColor = ContextCompat.getColor(
            parent.context,
            com.devsu.core_ui.R.color.default_background
        )
        sSelectedBackgroundColor =
            ContextCompat.getColor(
                parent.context,
                com.devsu.core_ui.R.color.selected_background
            )
        mDefaultCardImage = ContextCompat.getDrawable(
            parent.context,
            com.devsu.core_ui.R.drawable.ic_placeholder
        )

        val cardView = object : ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(cardView, false)
        return Presenter.ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val movie = item as Movie
        val cardView = viewHolder.view as ImageCardView
        val titleTV = cardView.findViewById<TextView>(androidx.leanback.R.id.title_text)
        val contentTV = cardView.findViewById<TextView>(androidx.leanback.R.id.content_text)

        titleTV.setTextColor(Color.WHITE)
        contentTV.setTextColor(Color.GRAY)
        contentTV.maxLines = Int.MAX_VALUE

        cardView.cardType = BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA;

        cardView.titleText = movie.title
        cardView.contentText = movie.originalTitle + "\n★${movie.voteAverage}   |   ${movie.releaseDate}"
        cardView.setMainImageDimensions(width, height)

        cardView.mainImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        cardView.mainImageView
            .load(movie.photoUrl){
                crossfade(true)
                placeholder(com.devsu.core_ui.R.drawable.ic_placeholder)
            }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        viewHolder?.let {
            val cardView = viewHolder.view as ImageCardView
            // Remove references to images so that the garbage collector can free up memory
            cardView.badgeImage = null
            cardView.mainImage = null
        }
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        view.infoAreaBackground = null
        val color = if (selected) sSelectedBackgroundColor else sDefaultBackgroundColor
        // Both background colors should be set because the view"s background is temporarily visible
        // during animations.
        view.setBackgroundColor(color)
        //view.setInfoAreaBackgroundColor(color)
    }

    companion object {
        private val TAG = "CardPresenter"
    }

}