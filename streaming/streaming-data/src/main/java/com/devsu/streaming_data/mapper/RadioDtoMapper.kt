package com.devsu.streaming_data.mapper

import android.webkit.URLUtil
import com.devsu.core.getValueOrNull
import com.devsu.streaming_data.remote.dto.radio.RadioDto
import com.devsu.streaming_domain.model.Radio

class RadioDtoMapper {
    fun mapToDomainModel(model: RadioDto): Radio? {
        return Radio(
            changeuuid = model.changeuuid ?: return null,
            stationuuid = model.stationuuid ?: return null,
            name = model.name ?: "unknown",
            urlResolved = getValidUrl(model.urlResolved) ?: return null,
            homepage = model.homepage,
            favicon = getValidUrl(model.favicon),
            tags = getTagList(model.tags),
            countrycode = model.countrycode?.getValueOrNull(),
            state = model.state,
            language = model.language ?: "unknown",
            votes = model.votes ?: 0L,
            codec = model.codec
        )
    }

    private fun getTagList(tags: String?): List<String>{
        tags?.let {  tags ->
            return tags.split(",").toList()
        }
        return emptyList()
    }

    private fun getValidUrl(url: String?): String?{
        url?.let {
            if(URLUtil.isValidUrl(it)) return it
        }
        return null
    }
}