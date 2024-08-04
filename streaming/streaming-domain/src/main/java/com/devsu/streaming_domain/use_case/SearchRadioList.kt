package com.devsu.streaming_domain.use_case

import com.devsu.core_ui.model.DataState
import com.devsu.core_ui.model.ProgressBarState
import com.devsu.core_ui.model.UIComponent
import com.devsu.streaming_domain.model.Radio
import com.devsu.streaming_domain.model.SearchRadioListParam
import com.devsu.streaming_domain.model.toQueryParamMap
import com.devsu.streaming_domain.repository.RadioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRadioList(
    private val repository: RadioRepository
) {
    fun execute(
        params: Map<SearchRadioListParam, Any>,
        limit: Int,
        page: Int,
        orderBy: SearchRadioListParam = SearchRadioListParam.Clickcount,
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
                    val movies = repository.searchRadioList(
                        params = params.toQueryParamMap(),
                        limit = limit,
                        page = page,
                        order = orderBy.param,
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