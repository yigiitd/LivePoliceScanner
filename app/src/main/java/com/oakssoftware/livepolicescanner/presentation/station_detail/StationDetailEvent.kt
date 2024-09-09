package com.oakssoftware.livepolicescanner.presentation.station_detail

import com.oakssoftware.livepolicescanner.domain.model.Station

sealed class StationDetailEvent {
    data class UpdatePlayState(val isPlaying: Boolean) : StationDetailEvent()
    data class UpdateStation(val station: Station) : StationDetailEvent()
}