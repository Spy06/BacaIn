package com.responsi.bacain.data.repository

import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.data.network.RetrofitClient

class AnimeRepository {

    private val api = RetrofitClient.animeApiService

    suspend fun getAnimeList(page: Int = 1): Result<List<Anime>> {
        return runCatching {
            api.getAnimeList(page = page).data
        }
    }

    suspend fun getAnimeDetail(id: Int): Result<Anime> {
        return runCatching {
            api.getAnimeDetail(id = id).data
        }
    }
}
