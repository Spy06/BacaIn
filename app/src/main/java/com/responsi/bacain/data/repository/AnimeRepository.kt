package com.responsi.bacain.data.repository

import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.data.network.RetrofitClient

class AnimeRepository {

    private val api = RetrofitClient.animeApiService

    /**
     * Fetches a paginated list of anime from Tenrai API.
     * Returns a [Result] wrapping the list or an exception.
     */
    suspend fun getAnimeList(page: Int = 1): Result<List<Anime>> {
        return runCatching {
            api.getAnimeList(page = page).data
        }
    }

    /**
     * Fetches detail for a single anime by MAL ID.
     * Returns a [Result] wrapping the anime or an exception.
     */
    suspend fun getAnimeDetail(id: Int): Result<Anime> {
        return runCatching {
            api.getAnimeDetail(id = id).data
        }
    }
}
