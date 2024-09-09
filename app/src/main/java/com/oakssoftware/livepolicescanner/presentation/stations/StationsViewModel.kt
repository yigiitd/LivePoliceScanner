package com.oakssoftware.livepolicescanner.presentation.stations

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oakssoftware.livepolicescanner.domain.model.Station
import com.oakssoftware.livepolicescanner.domain.use_case.get_stations.GetStationsUseCase
import com.oakssoftware.livepolicescanner.domain.use_case.update_stations.UpdateStationsUseCase
import com.oakssoftware.livepolicescanner.presentation.ScreenState
import com.oakssoftware.livepolicescanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val updateStationsUseCase: UpdateStationsUseCase
) : ViewModel() {

    private val _state =
        mutableStateOf(StationsState())
    val state: State<StationsState> = _state

    private var job: Job? = null

    init {
        getStations(isSearching = false, search = "")
    }

    private fun handleResource(resource: Resource<List<Station>>) {
        _state.value = when (resource) {
            is Resource.Success -> StationsState(
                screenState = ScreenState.Success,
                stations = resource.data ?: emptyList(),
                isFavoritesOpen = _state.value.isFavoritesOpen,
            )

            is Resource.Loading -> StationsState(
                screenState = ScreenState.Loading,
                isFavoritesOpen = _state.value.isFavoritesOpen
            )

            is Resource.Error -> StationsState(
                screenState = ScreenState.Error,
                errorMessage = resource.message ?: "Error!",
                isFavoritesOpen = _state.value.isFavoritesOpen,
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun getStations(isSearching: Boolean, search: String) {
        job?.cancel()

        job = flow { emit(search) }
            .flatMapLatest { query ->
                if (isSearching) delay(300)
                getStationsUseCase.executeGetStations(
                    isSearching,
                    query,
                    _state.value.isFavoritesOpen
                )
            }
            .onEach { handleResource(it) }
            .launchIn(viewModelScope)
    }

    private fun updateStation(station: Station) {
        updateStationsUseCase.executeUpdateStation(station).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val updatedStations = _state.value.stations.mapNotNull {
                        if (it.uid == station.uid) {
                            if (!_state.value.isFavoritesOpen) station else null
                        } else {
                            it
                        }
                    }
                    _state.value = _state.value.copy(stations = updatedStations)
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: StationsEvent) {
        when (event) {
            is StationsEvent.GetStations -> {
                getStations(event.isSearching, event.search)
            }

            is StationsEvent.UpdateStation -> {
                updateStation(event.station)
            }

            is StationsEvent.ToggleFavorites -> {
                _state.value = StationsState(
                    screenState = _state.value.screenState,
                    stations = _state.value.stations,
                    errorMessage = _state.value.errorMessage,
                    isFavoritesOpen = !_state.value.isFavoritesOpen
                )
                getStations(event.isSearching, event.search)
            }
        }
    }
}