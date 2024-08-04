package com.devsu.streaming_domain.model

sealed class SearchRadioListParam(val param: String) {
    object TagList : SearchRadioListParam("tagList")
    object CountryCode : SearchRadioListParam("countrycode")
    object Clickcount : SearchRadioListParam("clickcount")
    data class Custom(val customName: String): SearchRadioListParam(customName)

    companion object{
        fun fromString(rawValue: String): SearchRadioListParam {
            return when(rawValue.trim()){
                TagList.param -> TagList
                CountryCode.param -> CountryCode
                Clickcount.param -> Clickcount
                else -> Custom(rawValue)
            }
        }
    }
}

fun Map<SearchRadioListParam, Any>.toQueryParamMap(): Map<String, String> {
    return this.mapKeys { it.key.param }
        .mapValues { (_, value) -> value.toString() }
}