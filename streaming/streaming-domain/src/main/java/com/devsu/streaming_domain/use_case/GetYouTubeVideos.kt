package com.devsu.streaming_domain.use_case

import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.streaming_domain.model.YouTubeVideo
import com.devsu.streaming_domain.model.toQueryParamMap
import com.devsu.streaming_domain.repository.YouTubeVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetYouTubeVideos(
    private val repository: YouTubeVideoRepository
) {
    fun execute(
        channelId: String,
        isNetworkAvailable: Boolean
    ): Flow<DataState<List<YouTubeVideo>>> = flow{
        try {
            emit(DataState.Loading(
                progressBarState = ProgressBarState.Loading
            ))

            if(!isNetworkAvailable){
                emit(DataState.Response(UIComponent.None("Check your connection")))
            }else{
                try {
                    val movies = repository.searchVideos(
                        channelId = channelId,
                    )
                    emit(DataState.Data(movies))
                }catch (e: Exception){
                    emit(DataState.Response(UIComponent.None(e.message ?: "API Error")))
                }
            }
        } catch (e: Exception) {
            emit(DataState.Response(UIComponent.None(e.message ?: "Unknown Error")))
        }finally {
            emit(DataState.Loading(
                progressBarState = ProgressBarState.Idle
            ))
        }
    }
}