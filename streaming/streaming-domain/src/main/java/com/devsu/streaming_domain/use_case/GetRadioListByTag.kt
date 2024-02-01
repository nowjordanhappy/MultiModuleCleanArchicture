package com.devsu.streaming_domain.use_case

import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.repository.RadioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRadioListByTag(
    private val repository: RadioRepository
) {
    fun execute(
        tag: String,
        limit: Int,
        page: Int,
        isNetworkAvailable: Boolean
    ): Flow<DataState<List<Radio>>> = flow{
        try {
            emit(DataState.Loading(
                progressBarState = ProgressBarState.Loading
            ))

            if(!isNetworkAvailable){
                emit(DataState.Response(UIComponent.None("Check your connection")))
            }else{
                try {
                    val movies = repository.getRadioListByTag(
                        tag = tag,
                        limit = limit,
                        page = page,
                        order = "clickcount",
                        reverse = true
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