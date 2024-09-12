package com.oakssoftware.livepolicescanner.presentation.station_detail

import com.oakssoftware.livepolicescanner.domain.model.Station
import com.oakssoftware.livepolicescanner.presentation.ScreenState

data class StationDetailState(
    val screenState: ScreenState = ScreenState.Loading,
    val station: Station? = null,
    val favoriteStations: List<Station> = emptyList(),
    val errorMessage: String? = "",
)