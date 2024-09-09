package com.oakssoftware.livepolicescanner.presentation.stations

import androidx.compose.runtime.Immutable
import com.oakssoftware.livepolicescanner.domain.model.Station
import com.oakssoftware.livepolicescanner.presentation.ScreenState

@Immutable
data class StationsState(
    val screenState: ScreenState = ScreenState.Loading,
    val stations: List<Station> = emptyList(),
    val errorMessage: String = "",
    val isFavoritesOpen: Boolean = false
)