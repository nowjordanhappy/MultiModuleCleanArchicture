package com.devsu.movie_ui_mobile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.devsu.movie_domain.model.Movie
import com.devsu.movie_ui_mobile.R
import com.devsu.movie_ui_mobile.theme.Typography

@Composable
fun MovieListItem(
    movie: Movie,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clip(RoundedCornerShape(5.dp))
            ,//.height(170.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column {

            Image(
                modifier = Modifier.aspectRatio(1.5f),
                painter = rememberImagePainter(
                    data = movie.photoUrl,
                    builder = {
                        crossfade(true)
                        error(com.devsu.core_ui.R.drawable.ic_placeholder)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = movie.title
            )
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 5.dp)
                    .fillMaxHeight()
            ) {
                Text(movie.title, style = Typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(
                    movie.originalTitle,
                    style = Typography.labelMedium,
                    fontStyle = FontStyle.Italic,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Text(movie.releaseDate, style = Typography.labelSmall)
                    movie.voteAverage?.let { voteAverage ->
                        Text(
                            "★${voteAverage}",
                            style = Typography.labelSmall,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}