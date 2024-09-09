package com.oakssoftware.livepolicescanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oakssoftware.livepolicescanner.R
import com.oakssoftware.livepolicescanner.presentation.about_us.AboutUsScreen
import com.oakssoftware.livepolicescanner.presentation.home.HomeScreen
import com.oakssoftware.livepolicescanner.presentation.station_detail.view.StationDetailScreen
import com.oakssoftware.livepolicescanner.presentation.stations.view.StationsScreen
import com.oakssoftware.livepolicescanner.presentation.theme.PoliceScannerProTheme
import com.oakssoftware.livepolicescanner.util.Constants.STATION_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PoliceScannerProTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(innerPadding, navController)
                        }

                        composable(route = Screen.StationsScreen.route) {
                            StationsScreen(innerPadding, navController)
                        }

                        composable(route = Screen.StationDetailScreen.route + "/{$STATION_ID}") {
                            StationDetailScreen(innerPadding)
                        }

                        composable(route = Screen.AboutUsScreen.route) {
                            AboutUsScreen(innerPadding)
                        }
                    }
                }
            }
        }
    }
}