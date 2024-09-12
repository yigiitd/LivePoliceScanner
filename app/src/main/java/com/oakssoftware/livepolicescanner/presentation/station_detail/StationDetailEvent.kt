package com.oakssoftware.livepolicescanner.presentation.station_detail

import com.oakssoftware.livepolicescanner.domain.model.Station

sealed class StationDetailEvent {
    data class UpdateStation(val station: Station) : StationDetailEvent()
    data class UpdatePlayer(val player: MediaPlayer, val url: String? = null) : StationDetailEvent()

    enum class MediaPlayer {
        PLAY,
        PAUSE,
        STOP
    }
}