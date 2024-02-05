package com.devsu.streaming_ui_tv

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devsu.navigation.NavigationCommandSegment
import com.devsu.navigation.StreamingDirections
import com.devsu.streaming_ui_tv.choose_your_profile.ChooseYourProfile
import com.devsu.streaming_ui_tv.radio.main.RadioMainScreen
import com.devsu.streaming_ui_tv.radio.radio_player.RadioPlayerScreen
import com.devsu.streaming_ui_tv.radio.search_radio.RadioByTagScreen
import com.devsu.streaming_ui_tv.theme.StreamingUiMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@UnstableApi @OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamingUiMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    LaunchedEffect(viewModel.navigationCommandSegments){
                        viewModel.navigationCommandSegments.collect{ segment ->
                            println("NavigationManager main - command: $segment - ${segment.command.destination}")
                            if (segment.command.destination.isNotEmpty()) {
                                when(segment){
                                   is NavigationCommandSegment.DefaultNavigation-> navController.navigate(
                                       segment.command.destination,
                                       segment.navOptions,
                                       segment.navigatorExtras
                                   )
                                    is NavigationCommandSegment.BuilderNavigation -> navController.navigate(
                                        segment.command.destination,
                                        segment.builder
                                    )
                                    else -> {
                                        navController.navigate(segment.command.destination)
                                    }
                                }
                            }
                        }
                    }
                    val snackbarHostState = remember { SnackbarHostState() }
                    val lifecycleOwner = LocalLifecycleOwner.current

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(snackbarHostState)
                        }
                    ){ padding ->
                        NavHost(
                            navController,
                            startDestination = StreamingDirections.ChooseYourProfile.destination,
                            modifier = Modifier
                                .padding(padding)
                        ) {
                            composable(StreamingDirections.ChooseYourProfile.destination) {
                                ChooseYourProfile(
                                    scaffoldState = snackbarHostState
                                )
                            }

                            composable(StreamingDirections.RadioMain.destination) {
                                RadioMainScreen()
                            }

                            composable(StreamingDirections.searchRadio().route, arguments = StreamingDirections.searchRadio().arguments) {
                                RadioByTagScreen(
                                    scaffoldState = snackbarHostState
                                )
                            }
                            composable(StreamingDirections.radioPlayer().route, arguments = StreamingDirections.radioPlayer().arguments) { backStackEntry->
                                val stationuuid = backStackEntry.arguments?.getString(StreamingDirections.KEY_RADIO_STATION_UUID)
                                val name = backStackEntry.arguments?.getString(StreamingDirections.KEY_RADIO_NAME)
                                val favicon = backStackEntry.arguments?.getString(StreamingDirections.KEY_RADIO_FAVICON)
                                val urlResolved = backStackEntry.arguments?.getString(StreamingDirections.KEY_RADIO_URL_RESOLVED)
                                //val tagList = backStackEntry.arguments?.getStringArray(StreamingDirections.KEY_RADIO_TAG_LIST)?.toList()

                                Log.v("JordanRA", "stationuuid: $stationuuid - name: $name - favicon: $favicon - urlResolved: $urlResolved")

                                RadioPlayerScreen(
                                    scaffoldState = snackbarHostState,
                                    lifecycleOwner = lifecycleOwner,
                                )
                            }
                        }
                    }


                    /*Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(snackbarHostState)
                        }
                    ) { _ ->


                    }*/

                        /*val snackbarHostState = remember { SnackbarHostState() }
                        val lifecycleOwner = LocalLifecycleOwner.current
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            snackbarHost = {
                                SnackbarHost(snackbarHostState)
                            }
                        ){ _ ->


                            /*RadioPlayerScreen(
                                lifecycleOwner = lifecycleOwner
                            )*/
                            RadioByTag(scaffoldState = snackbarHostState)
                            //RadioMainScreen()
                            //YouTubeVideoPlayer(videoId = "Q4hMNpNZoLk", context = LocalContext.current)
                            /*ChooseYourProfile(
                                scaffoldState = snackbarHostState
                            )*/
                        }*/
                }
            }
        }
    }
}