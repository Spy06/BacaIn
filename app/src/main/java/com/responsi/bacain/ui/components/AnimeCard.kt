package com.responsi.bacain.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.responsi.bacain.data.model.Anime
import com.responsi.bacain.ui.theme.AnimeCyan
import com.responsi.bacain.ui.theme.AnimeDarkCard
import com.responsi.bacain.ui.theme.AnimeOnSurfaceVar
import com.responsi.bacain.ui.theme.AnimeViolet
import com.responsi.bacain.ui.theme.ScoreGold
import com.responsi.bacain.ui.theme.TagBgColor

/**
 * A card that displays an anime's core info (title, rating, year, episodes).
 */
@Composable
fun AnimeCard(
    anime: Anime,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AnimeDarkCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Gradient top stripe
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(AnimeViolet, AnimeCyan)
                    )
                )
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {

            // ── Type badge + Title ─────────────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                anime.type?.let { type ->
                    TypeBadge(type = type)
                }
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Info row ──────────────────────────────────────────────────
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Score
                ScoreBadge(score = anime.score)

                // Year
                anime.year?.let { year ->
                    InfoChip(label = year.toString())
                }

                // Episodes
                anime.episodes?.let { eps ->
                    InfoChip(label = "$eps ep")
                }
            }

            // ── Genres ────────────────────────────────────────────────────
            val genreNames = anime.genres?.take(3)?.map { it.name } ?: emptyList()
            if (genreNames.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    genreNames.forEach { genre ->
                        GenreTag(name = genre)
                    }
                }
            }
        }
    }
}

// ── Helper composables ────────────────────────────────────────────────────────

@Composable
private fun TypeBadge(type: String) {
    Box(
        modifier = Modifier
            .background(
                color = AnimeViolet.copy(alpha = 0.2f),
                shape = RoundedCornerShape(6.dp)
            )
            .border(1.dp, AnimeViolet.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.labelSmall,
            color = AnimeCyan,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ScoreBadge(score: Double?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Score",
            tint = ScoreGold,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = score?.let { String.format("%.2f", it) } ?: "N/A",
            style = MaterialTheme.typography.labelMedium,
            color = ScoreGold,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun InfoChip(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = AnimeOnSurfaceVar,
        fontSize = 12.sp
    )
}

@Composable
fun GenreTag(name: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(TagBgColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = AnimeCyan,
            fontSize = 11.sp
        )
    }
}

@Composable
fun InfoRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = AnimeOnSurfaceVar,
            modifier = Modifier.width(110.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
    }
}
