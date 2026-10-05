package com.responsi.bacain.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.ui.components.GenreTag
import com.responsi.bacain.ui.components.InfoRow
import com.responsi.bacain.ui.components.ScoreBadge
import com.responsi.bacain.ui.theme.AnimeCyan
import com.responsi.bacain.ui.theme.AnimeDarkCard
import com.responsi.bacain.ui.theme.AnimeNavy
import com.responsi.bacain.ui.theme.AnimeViolet
import com.responsi.bacain.ui.theme.AnimeVioletLight
import com.responsi.bacain.ui.viewmodel.AnimeDetailUiState
import com.responsi.bacain.ui.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    animeId: Int,
    onBack: () -> Unit,
    viewModel: DetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Trigger load when screen opens
    LaunchedEffect(animeId) {
        viewModel.loadDetail(animeId)
    }

    Scaffold(
        containerColor = AnimeNavy,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail Anime",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AnimeNavy,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {

                // ── Loading ───────────────────────────────────────────────
                is AnimeDetailUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                color = AnimeViolet,
                                modifier = Modifier.size(56.dp),
                                strokeWidth = 4.dp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Memuat detail...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // ── Success ───────────────────────────────────────────────
                is AnimeDetailUiState.Success -> {
                    AnimeDetailContent(anime = state.anime)
                }

                // ── Error ─────────────────────────────────────────────────
                is AnimeDetailUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(56.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Gagal memuat detail",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.message,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = { viewModel.loadDetail(animeId) },
                                colors = ButtonDefaults.buttonColors(containerColor = AnimeViolet)
                            ) {
                                Icon(Icons.Filled.Refresh, contentDescription = null)
                                Spacer(modifier = Modifier.size(8.dp))
                                Text("Coba Lagi")
                            }
                        }
                    }
                }
            }
        }
    }
}

// ── Detail content ────────────────────────────────────────────────────────────

@Composable
private fun AnimeDetailContent(anime: Anime) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // ── Header card with gradient border ──────────────────────────────
        Surface(
            color = AnimeDarkCard,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                // Title
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // English title
                anime.titleEnglish?.takeIf { it != anime.title }?.let { eng ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = eng,
                        style = MaterialTheme.typography.bodyMedium,
                        color = AnimeCyan
                    )
                }

                // Japanese title
                anime.titleJapanese?.let { jp ->
                    Text(
                        text = jp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Score + Status row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ScoreBadge(score = anime.score)
                    anime.status?.let { status ->
                        StatusChip(status = status)
                    }
                }
            }
        }

        // ── Info card ─────────────────────────────────────────────────────
        SectionCard(title = "Informasi") {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                InfoRow(label = "Judul", value = anime.title)
                anime.year?.let { InfoRow(label = "Tahun Rilis", value = it.toString()) }
                anime.season?.let { InfoRow(label = "Season", value = it.replaceFirstChar { c -> c.uppercase() }) }
                anime.episodes?.let { InfoRow(label = "Jumlah Episode", value = it.toString()) }
                anime.type?.let { InfoRow(label = "Tipe", value = it) }
                anime.source?.let { InfoRow(label = "Sumber", value = it) }
                anime.duration?.let { InfoRow(label = "Durasi", value = it) }
                anime.rating?.let { InfoRow(label = "Rating Usia", value = it) }
                anime.score?.let { InfoRow(label = "Skor MAL", value = String.format("%.2f", it)) }
                anime.scoredBy?.let { InfoRow(label = "Dinilai Oleh", value = "%,d pengguna".format(it)) }
                anime.rank?.let { InfoRow(label = "Rank", value = "#$it") }
                anime.popularity?.let { InfoRow(label = "Popularitas", value = "#$it") }
                anime.members?.let { InfoRow(label = "Members", value = "%,d".format(it)) }
                anime.favorites?.let { InfoRow(label = "Favorit", value = "%,d".format(it)) }
                anime.aired?.string?.let { InfoRow(label = "Tayang", value = it) }
                anime.studios?.takeIf { it.isNotEmpty() }?.let {
                    InfoRow(label = "Studio", value = it.joinToString(", ") { s -> s.name })
                }
            }
        }

        // ── Genres & Themes ───────────────────────────────────────────────
        val allTags = buildList {
            anime.genres?.let { addAll(it.map { g -> g.name }) }
            anime.themes?.let { addAll(it.map { g -> g.name }) }
            anime.demographics?.let { addAll(it.map { g -> g.name }) }
        }
        if (allTags.isNotEmpty()) {
            SectionCard(title = "Genre & Tema") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    allTags.forEach { tag ->
                        GenreTag(name = tag)
                    }
                }
            }
        }

        // ── Synopsis ──────────────────────────────────────────────────────
        anime.synopsis?.let { synopsis ->
            SectionCard(title = "Sinopsis") {
                Text(
                    text = synopsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify
                )
            }
        }

        // ── Background ───────────────────────────────────────────────────
        anime.background?.let { bg ->
            SectionCard(title = "Latar Belakang") {
                Text(
                    text = bg,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Justify
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// ── Section card wrapper ──────────────────────────────────────────────────────

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Surface(
        color = AnimeDarkCard,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = AnimeVioletLight
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Divider line
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(AnimeViolet, AnimeCyan, Color.Transparent)
                        )
                    )
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

// ── Status chip ───────────────────────────────────────────────────────────────

@Composable
private fun StatusChip(status: String) {
    val color = when {
        status.contains("Airing", ignoreCase = true) -> AnimeCyan
        status.contains("Finished", ignoreCase = true) -> Color(0xFF4CAF50)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.SemiBold
        )
    }
}
