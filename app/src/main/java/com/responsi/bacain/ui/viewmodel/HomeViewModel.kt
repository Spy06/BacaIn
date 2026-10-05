package com.responsi.bacain.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// ── UI State ──────────────────────────────────────────────────────────────────

sealed class AnimeListUiState {
    object Loading : AnimeListUiState()
    data class Success(val animeList: List<Anime>) : AnimeListUiState()
    data class Error(val message: String) : AnimeListUiState()
}

// ── ViewModel ─────────────────────────────────────────────────────────────────

class HomeViewModel(
    private val repository: AnimeRepository = AnimeRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnimeListUiState>(AnimeListUiState.Loading)
    val uiState: StateFlow<AnimeListUiState> = _uiState.asStateFlow()

    private var currentPage = 1
    private val _animeList = mutableListOf<Anime>()

    init {
        loadAnime()
    }

    fun loadAnime(refresh: Boolean = false) {
        if (refresh) {
            currentPage = 1
            _animeList.clear()
        }
        viewModelScope.launch {
            _uiState.value = AnimeListUiState.Loading
            repository.getAnimeList(page = currentPage)
                .onSuccess { list ->
                    _animeList.addAll(list)
                    _uiState.value = AnimeListUiState.Success(_animeList.toList())
                    currentPage++
                }
                .onFailure { throwable ->
                    _uiState.value = AnimeListUiState.Error(
                        throwable.message ?: "Terjadi kesalahan saat memuat data anime"
                    )
                }
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            repository.getAnimeList(page = currentPage)
                .onSuccess { list ->
                    _animeList.addAll(list)
                    _uiState.value = AnimeListUiState.Success(_animeList.toList())
                    currentPage++
                }
                .onFailure { /* Silently ignore pagination errors */ }
        }
    }
}
