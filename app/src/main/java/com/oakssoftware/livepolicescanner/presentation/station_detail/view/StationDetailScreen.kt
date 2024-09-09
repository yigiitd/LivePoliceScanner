package com.oakssoftware.livepolicescanner.presentation.station_detail.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oakssoftware.livepolicescanner.R
import com.oakssoftware.livepolicescanner.presentation.ScreenState
import com.oakssoftware.livepolicescanner.presentation.station_detail.StationDetailEvent
import com.oakssoftware.livepolicescanner.presentation.station_detail.StationDetailViewModel

@Composable
fun StationDetailScreen(
    ip: PaddingValues,
    viewModel: StationDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(ip),
    ) {
        when (state.screenState) {
            ScreenState.Success -> {
                if (state.station != null) {
                    val station = state.station
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ElevatedCard(
                                modifier = Modifier
                                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 8.dp)
                                    .fillMaxWidth()
                                    .weight(0.615f),
                                shape = MaterialTheme.shapes.large,
                                colors = CardDefaults.cardColors()
                                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 32.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 8.dp
                                        ),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.front),
                                        contentDescription = "App Icon",
                                        contentScale = ContentScale.Fit,
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                                        modifier = Modifier
                                            .size(216.dp)
                                            .border(
                                                BorderStroke(
                                                    4.dp,
                                                    MaterialTheme.colorScheme.primary
                                                ),
                                                CircleShape
                                            )
                                            .padding(4.dp)
                                            .clip(RoundedCornerShape(216.dp))
                                            .background(MaterialTheme.colorScheme.surfaceContainer)
                                    )

                                    Text(
                                        text = station.name,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top = 32.dp),
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        maxLines = 2
                                    )

                                    Text(
                                        text = station.location,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top = 6.dp),
                                        style = MaterialTheme.typography.bodyLarge,
                                        maxLines = 2
                                    )
                                }
                            }

                            ElevatedCard(
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxWidth()
                                    .weight(0.385f),
                                shape = MaterialTheme.shapes.large,
                                colors = CardDefaults.cardColors()
                                    .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            top = 16.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 16.dp
                                        ),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        FloatingActionButton(
                                            shape = AbsoluteRoundedCornerShape(216.dp),
                                            onClick = {
                                                viewModel.onEvent(
                                                    StationDetailEvent.UpdatePlayState(
                                                        true
                                                    )
                                                )
                                            }) {
                                            Icon(
                                                imageVector = Icons.Default.PlayArrow,
                                                contentDescription = "Favorite Stations",
                                                modifier = Modifier.size(32.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.padding(8.dp))

                                        FloatingActionButton(
                                            shape = AbsoluteRoundedCornerShape(216.dp),
                                            onClick = {
                                                viewModel.onEvent(
                                                    StationDetailEvent.UpdatePlayState(
                                                        false
                                                    )
                                                )
                                            }) {
                                            Icon(
                                                painter = painterResource(R.drawable.baseline_stop_24),
                                                contentDescription = "Favorite Stations",
                                                modifier = Modifier.size(32.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        "There was an error with getting the station",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            ScreenState.Error -> {
                Text(
                    "There was an error with getting the station",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }

            ScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}