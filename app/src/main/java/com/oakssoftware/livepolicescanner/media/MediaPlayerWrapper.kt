package com.oakssoftware.livepolicescanner.media

import android.media.AudioAttributes
import android.media.MediaPlayer

class MediaPlayerWrapper {
    private var mediaPlayer: MediaPlayer? = null
    private var currentState = MediaState(MediaPlayerState.IDLE, false)

    enum class MediaPlayerState {
        IDLE,
        PLAYING,
        PAUSED,
        STOPPED
    }

    data class MediaState(
        val mediaPlayerState: MediaPlayerState,
        val isConnectionEstablished: Boolean
    )

    fun playMedia(url: String) {
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
                    currentState = currentState.copy(
                        mediaPlayerState = MediaPlayerState.PLAYING,
                        isConnectionEstablished = true
                    )
                }
            }
        } else {
            if (currentState.mediaPlayerState == MediaPlayerState.PAUSED) {
                mediaPlayer?.start()
                currentState = currentState.copy(mediaPlayerState = MediaPlayerState.PLAYING)
            }
        }
    }

    fun pauseMedia() {
        if (currentState.mediaPlayerState == MediaPlayerState.PLAYING) {
            mediaPlayer?.pause()
            currentState = currentState.copy(mediaPlayerState = MediaPlayerState.PAUSED)
        }
    }

    fun stopMedia() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentState = currentState.copy(mediaPlayerState = MediaPlayerState.STOPPED)
    }

    fun getCurrentState(): MediaState = currentState
}