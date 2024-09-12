package com.oakssoftware.livepolicescanner.presentation.station_detail

data class MediaState(
    val playerState: PlayerState,
    val isConnectionEstablished: Boolean
)

enum class PlayerState {
    IDLE,
    PLAYING,
    PAUSED,
    STOPPED
}