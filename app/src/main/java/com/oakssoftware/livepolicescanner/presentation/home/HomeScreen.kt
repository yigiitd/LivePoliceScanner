package com.oakssoftware.livepolicescanner.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.oakssoftware.livepolicescanner.R
import com.oakssoftware.livepolicescanner.presentation.Screen
import com.oakssoftware.livepolicescanner.presentation.theme.PoliceScannerProTheme

@Composable
fun HomeScreen(
    ip: PaddingValues,
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize().padding(ip)) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Live Police Scanner",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 64.dp),
                fontSize = 32.sp
            )

            Text(
                text = "Police Radio Feeds",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 24.sp
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                MenuButton("Police Scanner") {
                    navController.navigate(Screen.StationsScreen.route)
                }
                Spacer(modifier = Modifier.padding(16.dp))

                MenuButton(text = "About Us") {
                    navController.navigate(Screen.AboutUsScreen.route)
                }
                Spacer(modifier = Modifier.padding(16.dp))

                MenuButton("Share") {

                }
                Spacer(modifier = Modifier.padding(16.dp))

                MenuButton("Rate Us") {

                }
            }
        }
    }
}