package com.responsi.bacain.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AnimeDetailUiState {
    object Loading : AnimeDetailUiState()
    data class Success(val anime: Anime) : AnimeDetailUiState()
    data class Error(val message: String) : AnimeDetailUiState()
}

class DetailViewModel(
    private val repository: AnimeRepository = AnimeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeDetailUiState>(AnimeDetailUiState.Loading)
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()

    fun loadDetail(animeId: Int) {
        viewModelScope.launch {
            _uiState.value = AnimeDetailUiState.Loading
            repository.getAnimeDetail(id = animeId)
                .onSuccess { anime ->
                    _uiState.value = AnimeDetailUiState.Success(anime)
                }
                .onFailure { throwable ->
                    _uiState.value = AnimeDetailUiState.Error(
                        throwable.message ?: "Terjadi kesalahan saat memuat detail anime"
                    )
                }
        }
    }
}
