package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.AcademyEvent
import com.example.model.GalleryItem
import com.example.model.StudentAchievement
import com.example.ui.components.ClassicalBadge
import com.example.ui.components.ClassicalCard
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.Screen

@Composable
fun AchievementsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val achievements = viewModel.achievements.value
    var selectedFilter by remember { mutableStateOf("All") }

    val categories = listOf("All", "Competition", "Examination", "Performance", "Arangetram")

    val filtered = remember(achievements, selectedFilter) {
        if (selectedFilter == "All") achievements
        else achievements.filter { it.category.equals(selectedFilter, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Student Achievements",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Celebrating the discipline, awards, and artistic milestones of our disciples",
                    fontSize = 13.sp,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedFilter == cat,
                            onClick = { selectedFilter = cat },
                            label = { Text(cat, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaroonPrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        items(filtered) { ach ->
            AchievementCard(achievement = ach)
        }
    }
}

@Composable
fun AchievementCard(achievement: StudentAchievement) {
    val drawableId = when (achievement.imageResName) {
        "img_guru_hetal" -> R.drawable.img_guru_hetal
        "img_dance_class" -> R.drawable.img_dance_class
        "img_hero_banner" -> R.drawable.img_hero_banner
        else -> R.drawable.img_performance_event
    }

    ClassicalCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.EmojiEvents, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = achievement.medalOrAward,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaroonPrimary
                )
            }
            ClassicalBadge(text = achievement.year)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = achievement.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = achievement.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TextCharcoal
                )
                Text(
                    text = "Honoree: ${achievement.studentName}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaroonPrimary
                )
                Text(
                    text = achievement.eventOrExam,
                    fontSize = 11.sp,
                    color = TextSubtle
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = achievement.details,
            fontSize = 12.sp,
            color = TextMuted,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun EventsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val events = viewModel.events.value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Academy Events & Recitals",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Annual showcases, workshops, and Gandharva Mahavidyalaya examination dates",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        items(events) { event ->
            EventFullCard(
                event = event,
                onRegisterClick = { viewModel.navigateTo(Screen.AdmissionForm) }
            )
        }
    }
}

@Composable
fun EventFullCard(
    event: AcademyEvent,
    onRegisterClick: () -> Unit
) {
    val drawableId = when (event.imageResName) {
        "img_guru_hetal" -> R.drawable.img_guru_hetal
        "img_dance_class" -> R.drawable.img_dance_class
        "img_hero_banner" -> R.drawable.img_hero_banner
        else -> R.drawable.img_performance_event
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = event.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f))
                )
                if (event.isRegistrationOpen) {
                    Surface(
                        color = GoldAccent,
                        shape = RoundedCornerShape(topStart = 0.dp, bottomStart = 8.dp, bottomEnd = 0.dp, topEnd = 0.dp),
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = "REGISTRATION OPEN",
                            color = MaroonPrimaryDark,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = event.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TextCharcoal
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, tint = MaroonPrimary, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "${event.date} • ${event.time}", fontSize = 12.sp, color = MaroonPrimary, fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = TextSubtle, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = event.location, fontSize = 12.sp, color = TextMuted)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = event.description,
                    fontSize = 12.sp,
                    color = TextCharcoal,
                    lineHeight = 18.sp
                )

                if (event.isRegistrationOpen) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = onRegisterClick,
                        colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .testTag("event_register_${event.id}")
                    ) {
                        Text("Register / Reserve Seats", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val gallery = viewModel.gallery.value
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("All", "Performances", "Classes", "Events", "Guru", "Arangetram", "Workshops")

    val filtered = remember(gallery, selectedCategory) {
        if (selectedCategory == "All") gallery
        else gallery.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Studio Media Gallery",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Visual moments of devotion, practice, stage recitals, and celebrations",
                    fontSize = 13.sp,
                    color = TextMuted
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { cat ->
                        FilterChip(
                            selected = selectedCategory == cat,
                            onClick = { selectedCategory = cat },
                            label = { Text(cat, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaroonPrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }
        }

        // Two columns grid emulation inside LazyColumn
        val chunked = filtered.chunked(2)
        items(chunked) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    Box(modifier = Modifier.weight(1f)) {
                        GalleryGridTile(
                            item = item,
                            onClick = { viewModel.openGalleryViewer(item) }
                        )
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun GalleryGridTile(
    item: GalleryItem,
    onClick: () -> Unit
) {
    val drawableId = when (item.imageResName) {
        "img_guru_hetal" -> R.drawable.img_guru_hetal
        "img_dance_class" -> R.drawable.img_dance_class
        "img_hero_banner" -> R.drawable.img_hero_banner
        else -> R.drawable.img_performance_event
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                ClassicalBadge(
                    text = item.category,
                    containerColor = MaroonPrimaryDark.copy(alpha = 0.85f),
                    contentColor = GoldLight
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }
    }
}
