package com.responsi.bacain.data.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("pagination") val pagination: Pagination?,
    @SerializedName("data") val data: List<Anime>
)

data class AnimeDetailResponse(
    @SerializedName("data") val data: Anime
)

data class Pagination(
    @SerializedName("last_visible_page") val lastVisiblePage: Int,
    @SerializedName("has_next_page") val hasNextPage: Boolean,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("items") val items: PaginationItems?
)

data class PaginationItems(
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("per_page") val perPage: Int
)

data class Anime(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("url") val url: String?,
    @SerializedName("images") val images: AnimeImages?,
    @SerializedName("title") val title: String,
    @SerializedName("title_english") val titleEnglish: String?,
    @SerializedName("title_japanese") val titleJapanese: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("source") val source: String?,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("airing") val airing: Boolean?,
    @SerializedName("aired") val aired: Aired?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("rating") val rating: String?,
    @SerializedName("score") val score: Double?,
    @SerializedName("scored_by") val scoredBy: Int?,
    @SerializedName("rank") val rank: Int?,
    @SerializedName("popularity") val popularity: Int?,
    @SerializedName("members") val members: Int?,
    @SerializedName("favorites") val favorites: Int?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("background") val background: String?,
    @SerializedName("season") val season: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("themes") val themes: List<Genre>?,
    @SerializedName("demographics") val demographics: List<Genre>?,
    @SerializedName("studios") val studios: List<Studio>?
)

data class AnimeImages(
    @SerializedName("jpg") val jpg: ImageVariants?,
    @SerializedName("webp") val webp: ImageVariants?
)

data class ImageVariants(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("small_image_url") val smallImageUrl: String?,
    @SerializedName("large_image_url") val largeImageUrl: String?
)

data class Aired(
    @SerializedName("from") val from: String?,
    @SerializedName("to") val to: String?,
    @SerializedName("string") val string: String?
)

data class Genre(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("type") val type: String?,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String?
)

data class Studio(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("type") val type: String?,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String?
)
