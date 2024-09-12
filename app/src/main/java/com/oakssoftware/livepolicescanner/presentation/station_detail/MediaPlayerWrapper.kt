package com.oakssoftware.livepolicescanner.presentation.station_detail

import android.media.AudioAttributes
import android.media.MediaPlayer

class MediaPlayerWrapper {
    private var mediaPlayer: MediaPlayer? = null
    private var currentState = MediaPlayerState.IDLE

    enum class MediaPlayerState {
        IDLE,
        PLAYING,
        PAUSED,
        STOPPED
    }

    fun play(url: String) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(url)
                prepareAsync()
                setOnPreparedListener {
                    start()
                    currentState = MediaPlayerState.PLAYING
                }
            }
        } else {
            if (currentState == MediaPlayerState.PAUSED) {
                mediaPlayer?.start()
                currentState = MediaPlayerState.PLAYING
            }
        }
    }

    fun pause() {
        if (currentState == MediaPlayerState.PLAYING) {
            mediaPlayer?.pause()
            currentState = MediaPlayerState.PAUSED
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentState = MediaPlayerState.STOPPED
    }

    fun getCurrentState(): MediaPlayerState = currentState
}