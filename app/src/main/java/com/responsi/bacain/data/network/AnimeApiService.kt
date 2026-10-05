package com.responsi.bacain.data.network

import com.responsi.bacain.data.model.AnimeDetailResponse
import com.responsi.bacain.data.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {

    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 25
    ): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse
}
