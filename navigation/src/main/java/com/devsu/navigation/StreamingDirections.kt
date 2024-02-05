package com.devsu.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object StreamingDirections {
    const val KEY_RADIO_STATION_UUID = "radioStationuuid"
    const val KEY_RADIO_NAME = "radioName"
    const val KEY_RADIO_FAVICON = "radioFavicon"
    const val KEY_RADIO_TAG_LIST = "radioTagList"
    const val KEY_RADIO_URL_RESOLVED = "radioUrlResolved"
    const val KEY_RADIO_SEARCH_KEY = "radioSearchKey"
    const val KEY_RADIO_SEARCH_VALUE = "radioSearchValue"

    private const val DEFAULT_NULL_VALUE = "null"

    val Default = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
        override val route: String = destination
    }

    val RadioMain = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "radio_main"
        override val route: String = destination
    }

    val ChooseYourProfile = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "choose_your_profile"
        override val route: String = destination
    }

    fun searchRadio(
        key: String? = null,
        value: String? = null
    ) = object : NavigationCommand {
        override val arguments = listOf(
            navArgument(KEY_RADIO_SEARCH_KEY) { type = NavType.StringType},
            navArgument(KEY_RADIO_SEARCH_VALUE) { type = NavType.StringType}
        )

        override val route: String = "search_radio/{$KEY_RADIO_SEARCH_KEY}/{$KEY_RADIO_SEARCH_VALUE}"

        override val destination = "search_radio/$key/$value"
    }

    fun radioPlayer(
        stationuuid: String? = null,
        name: String? = null,
        favicon: String? = null,
        urlResolved: String? = null,
        tagList: List<String> = emptyList()
    ) = object : NavigationCommand {
        override val route = "radio_player/{$KEY_RADIO_STATION_UUID}/{$KEY_RADIO_NAME}/{$KEY_RADIO_FAVICON}/{$KEY_RADIO_URL_RESOLVED}"///{$KEY_RADIO_TAG_LIST}"

        override val arguments = listOf(
            navArgument(KEY_RADIO_STATION_UUID) { type = NavType.StringType },
            navArgument(KEY_RADIO_NAME) { type = NavType.StringType },
                navArgument(KEY_RADIO_FAVICON) { type = NavType.StringType; nullable = true },
                navArgument(KEY_RADIO_URL_RESOLVED) { type = NavType.StringType; nullable = true },
                //navArgument(KEY_RADIO_TAG_LIST) { type = NavType.StringArrayType },
            )

            override val destination = "radio_player/$stationuuid/$name/$favicon/${urlResolved}"///${tagList.joinToString(",")}"
        }

}