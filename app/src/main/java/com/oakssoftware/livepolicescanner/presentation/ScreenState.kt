package com.oakssoftware.livepolicescanner.presentation

sealed class ScreenState{
    data object Success : ScreenState()
    data object Loading : ScreenState()
    data object Error : ScreenState()
}