package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.AcademyEvent
import com.example.model.ClassItem
import com.example.model.StudioInfo
import com.example.ui.components.CardCornerMotifs
import com.example.ui.components.ClassicalBadge
import com.example.ui.components.ClassicalCard
import com.example.ui.components.MandalaWatermark
import com.example.ui.components.TempleOrnamentalDivider
import com.example.ui.components.TempleToranArch
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.Screen

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val studioInfo = viewModel.studioInfo.value
    val classes = viewModel.classes.value
    val events = viewModel.events.value
    val achievements = viewModel.achievements.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Decorative background subtle mandala watermark
        MandalaWatermark(
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = 60.dp),
            color = GoldAccent.copy(alpha = 0.05f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            // Hero Section with Temple Arch & Gold Border
            item {
                HomeHeroSection(
                    studioInfo = studioInfo,
                    onJoinClicked = { viewModel.navigateTo(Screen.AdmissionForm) },
                    onExploreClassesClicked = { viewModel.navigateTo(Screen.Classes) }
                )
            }

            // Decorative Toran arch below hero
            item {
                TempleToranArch(
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                    color = GoldAccent
                )
            }

            // Quick Actions Grid
            item {
                QuickActionsSection(
                    onActionSelected = { screen -> viewModel.navigateTo(screen) }
                )
            }

            // Classical Decorative Ornamental Divider
            item {
                TempleOrnamentalDivider(modifier = Modifier.padding(horizontal = 24.dp))
            }

            // Philosophy / Sacred Bharatanatyam Callout with Gold corner accents
            item {
                PaddingValues(horizontal = 16.dp, vertical = 6.dp).let {
                    Box(modifier = Modifier.padding(it)) {
                        ClassicalCard(
                            backgroundColor = MaroonPrimaryDark,
                            borderColor = GoldAccent
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "🕉", fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "“Natya: The Sacred Union of Bhava, Raga & Tala”",
                                    color = GoldLight,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "🕉", fontSize = 16.sp)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Rooted in the ancient Natyashastra treatise, Bharatanatyam cultivates profound devotion, physical endurance, poise, and spiritual storytelling. Nrutyashala nurtures every aspiring dancer with dedicated Guru-Shishya mentorship.",
                                color = Color.White.copy(alpha = 0.92f),
                                fontSize = 13.sp,
                                lineHeight = 21.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            // Featured Classes Horizontal Carousel
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionHeader(
                    title = "Featured Training Programs",
                    subtitle = "Offline Guru-Kula and Global Online Learning",
                    actionText = "View All",
                    onActionClick = { viewModel.navigateTo(Screen.Classes) }
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(classes.take(3)) { classItem ->
                        FeaturedClassCard(
                            classItem = classItem,
                            onEnrollClick = { viewModel.navigateTo(Screen.AdmissionForm) },
                            onDetailsClick = { viewModel.navigateTo(Screen.Classes) }
                        )
                    }
                }
            }

            // Meet Your Guru Teaser
            item {
                Spacer(modifier = Modifier.height(18.dp))
                MeetGuruTeaserCard(
                    onMeetGuruClick = { viewModel.navigateTo(Screen.MeetGuru) }
                )
            }

            // Upcoming Events Teaser
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(
                    title = "Upcoming Events & Showcases",
                    subtitle = "Recitals, Workshops & Board Examinations",
                    actionText = "All Events",
                    onActionClick = { viewModel.navigateTo(Screen.Events) }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    events.take(2).forEach { event ->
                        UpcomingEventMiniCard(
                            event = event,
                            onRegister = { viewModel.navigateTo(Screen.AdmissionForm) }
                        )
                    }
                }
            }

            // Student Achievements Showcase
            item {
                Spacer(modifier = Modifier.height(18.dp))
                SectionHeader(
                    title = "Academy Achievements",
                    subtitle = "Competitions, Honors & Solo Arangetrams",
                    actionText = "Explore",
                    onActionClick = { viewModel.navigateTo(Screen.Achievements) }
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(achievements.take(3)) { ach ->
                        AchievementMiniCard(ach = ach, onClick = { viewModel.navigateTo(Screen.Achievements) })
                    }
                }
            }

            // Bottom Decorative Quote & Traditional Sanskrit Sloka with Mandalas
            item {
                Spacer(modifier = Modifier.height(26.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TempleOrnamentalDivider(color = GoldAccent)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "“Angikam Bhuvanam Yasya, Vachikam Sarva Vangmayam\nAharyam Chandra Taradi, Tam Numah Satvikam Shivam”",
                        color = MaroonPrimary,
                        fontSize = 12.5.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "— Natyashastra Invocatory Sloka (Dedicated to Nataraja)",
                        color = TextSubtle,
                        fontSize = 11.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TempleToranArch(color = GoldMuted)
                }
            }
        }
    }
}

@Composable
fun HomeHeroSection(
    studioInfo: StudioInfo,
    onJoinClicked: () -> Unit,
    onExploreClassesClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(355.dp)
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(GoldAccent, GoldAccent.copy(alpha = 0.3f), GoldAccent)
                ),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
    ) {
        // Background Hero Image
        Image(
            painter = painterResource(id = R.drawable.img_hero_banner),
            contentDescription = "Bharatanatyam dancer hero banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay for readability and dramatic temple lighting
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaroonPrimaryDark.copy(alpha = 0.70f),
                            MaroonPrimaryDark.copy(alpha = 0.88f),
                            MaroonPrimaryDark.copy(alpha = 0.98f)
                        )
                    )
                )
        )

        // Subtle background mandala overlay
        MandalaWatermark(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.TopEnd)
                .offset(x = 60.dp, y = -40.dp),
            color = GoldLight.copy(alpha = 0.08f)
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Academy Badge with Gold Shimmer look
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(MaroonPrimary, MaroonPrimaryDark, MaroonPrimary)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(1.5.dp, GoldAccent, RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(text = "✦", color = GoldAccent, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "PREMIER BHARATANATYAM ACADEMY",
                    color = GoldLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "✦", color = GoldAccent, fontSize = 12.sp)
            }

            // Titles
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Nrutyashala",
                    color = GoldLight,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "— THE DANCE STUDIO —",
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 2.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "“Where tradition meets dedication, discipline and artistic expression.”",
                    color = GoldAccent,
                    fontSize = 13.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
            }

            // Dual CTA Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onJoinClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("hero_join_btn")
                        .height(48.dp)
                        .border(1.dp, GoldLight, RoundedCornerShape(12.dp))
                ) {
                    Text(
                        text = "Join Nrutyashala",
                        color = MaroonPrimaryDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                OutlinedButton(
                    onClick = onExploreClassesClicked,
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, GoldLight),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("hero_explore_classes_btn")
                        .height(48.dp)
                ) {
                    Text(
                        text = "Explore Classes",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionsSection(
    onActionSelected: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "❖", color = GoldAccent, fontSize = 13.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Quick Academy Access",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaroonPrimary,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionButton(
                title = "Join Class",
                icon = Icons.Default.School,
                onClick = { onActionSelected(Screen.AdmissionForm) }
            )
            QuickActionButton(
                title = "Meet Guru",
                icon = Icons.Default.Person,
                onClick = { onActionSelected(Screen.MeetGuru) }
            )
            QuickActionButton(
                title = "Online Class",
                icon = Icons.Default.Videocam,
                onClick = { onActionSelected(Screen.Classes) }
            )
            QuickActionButton(
                title = "Achievements",
                icon = Icons.Default.EmojiEvents,
                onClick = { onActionSelected(Screen.Achievements) }
            )
            QuickActionButton(
                title = "Contact",
                icon = Icons.Default.Phone,
                onClick = { onActionSelected(Screen.ContactUs) }
            )
        }
    }
}

@Composable
fun QuickActionButton(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(62.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        listOf(GoldContainer, WarmSand)
                    )
                )
                .border(1.5.dp, GoldAccent, CircleShape)
                .shadow(2.dp, CircleShape, spotColor = GoldMuted),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaroonPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextCharcoal,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "✦", color = GoldAccent, fontSize = 12.sp, modifier = Modifier.padding(end = 6.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaroonPrimary,
                    fontFamily = FontFamily.Serif
                )
            }
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextMuted,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
        if (actionText != null && onActionClick != null) {
            TextButton(
                onClick = onActionClick,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = actionText,
                    color = MaroonPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaroonPrimary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun FeaturedClassCard(
    classItem: ClassItem,
    onEnrollClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .border(1.2.dp, GoldAccent.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
            .clickable(onClick = onDetailsClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CardCornerMotifs(color = GoldAccent.copy(alpha = 0.5f))

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClassicalBadge(
                        text = classItem.type.uppercase(),
                        containerColor = if (classItem.type == "Online") Color(0xFFE8F5E9) else GoldContainer,
                        contentColor = if (classItem.type == "Online") SuccessGreen else MaroonPrimaryDark
                    )
                    Text(
                        text = classItem.level,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaroonPrimary
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = classItem.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextCharcoal,
                    maxLines = 2,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = classItem.description,
                    fontSize = 12.sp,
                    color = TextMuted,
                    maxLines = 2,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = GoldMuted,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "${classItem.schedule} • ${classItem.timing}",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onEnrollClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                ) {
                    Text("Enroll Now", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MeetGuruTeaserCard(
    onMeetGuruClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.5.dp, GoldAccent, RoundedCornerShape(18.dp))
            .clickable(onClick = onMeetGuruClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = CreamSurfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CardCornerMotifs(color = GoldAccent.copy(alpha = 0.6f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img_guru_hetal),
                        contentDescription = "Guru Hetal",
                        modifier = Modifier
                            .size(86.dp)
                            .clip(CircleShape)
                            .border(2.5.dp, GoldAccent, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    // Small golden namaste / lotus badge on corner
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(GoldAccent)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "✦", color = MaroonPrimaryDark, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "❖", color = MaroonPrimary, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "MEET YOUR GURU",
                            color = MaroonPrimary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    Text(
                        text = "Guru Hetal",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = TextCharcoal
                    )
                    Text(
                        text = "B.A. in Bharatanatyam • Visharad • Akhil Bhartiya Gandharva Mahavidyalaya Registered Teacher",
                        fontSize = 11.sp,
                        color = TextMuted,
                        lineHeight = 16.sp,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Read Biography & Qualifications",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaroonPrimary
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = MaroonPrimary,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UpcomingEventMiniCard(
    event: AcademyEvent,
    onRegister: () -> Unit
) {
    ClassicalCard(borderColor = GoldAccent.copy(alpha = 0.5f)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = event.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TextCharcoal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaroonPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${event.date} • ${event.time}",
                        fontSize = 12.sp,
                        color = MaroonPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        tint = TextSubtle,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.location,
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }
            if (event.isRegistrationOpen) {
                Button(
                    onClick = onRegister,
                    colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Register", color = MaroonPrimaryDark, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AchievementMiniCard(
    ach: com.example.model.StudentAchievement,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .border(1.2.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CardCornerMotifs(color = GoldAccent.copy(alpha = 0.4f))

            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClassicalBadge(text = ach.category, containerColor = GoldContainer)
                    Text(text = ach.year, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = ach.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = TextCharcoal,
                    maxLines = 1
                )
                Text(
                    text = ach.studentName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaroonPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = ach.details,
                    fontSize = 11.sp,
                    color = TextMuted,
                    maxLines = 2,
                    lineHeight = 15.sp
                )
            }
        }
    }
}
