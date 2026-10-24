package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ClassItem
import com.example.model.LearningLevel
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
fun ClassesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val classes = viewModel.classes.value
    var selectedTab by remember { mutableStateOf(0) } // 0 = All, 1 = Offline, 2 = Online

    val filteredClasses = remember(classes, selectedTab) {
        when (selectedTab) {
            1 -> classes.filter { it.type == "Offline" }
            2 -> classes.filter { it.type == "Online" }
            else -> classes
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MandalaWatermark(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 100.dp, y = 50.dp),
            color = GoldAccent.copy(alpha = 0.05f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "✦", color = GoldAccent, fontSize = 14.sp, modifier = Modifier.padding(end = 6.dp))
                        Text(
                            text = "Bharatanatyam Programs",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimary
                        )
                    }
                    Text(
                        text = "Authentic training paths tailored for in-studio and global online students",
                        fontSize = 13.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    // Decorative Tabs
                    TabRow(
                        selectedTabIndex = selectedTab,
                        containerColor = CreamSurfaceVariant,
                        contentColor = MaroonPrimary,
                        modifier = Modifier
                            .border(1.2.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    ) {
                        Tab(
                            selected = selectedTab == 0,
                            onClick = { selectedTab = 0 },
                            text = { Text("All Classes", fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal) }
                        )
                        Tab(
                            selected = selectedTab == 1,
                            onClick = { selectedTab = 1 },
                            text = { Text("Offline Studio", fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal) }
                        )
                        Tab(
                            selected = selectedTab == 2,
                            onClick = { selectedTab = 2 },
                            text = { Text("Online Global", fontWeight = if (selectedTab == 2) FontWeight.Bold else FontWeight.Normal) }
                        )
                    }
                }
            }

            items(filteredClasses) { classItem ->
                ClassDetailCard(
                    classItem = classItem,
                    onEnrollClick = { viewModel.navigateTo(Screen.AdmissionForm) }
                )
            }

            item {
                // Learning levels promotion card with traditional border
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GoldContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CardCornerMotifs(color = GoldAccent.copy(alpha = 0.6f))

                        Row(
                            modifier = Modifier.padding(18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "❖", color = MaroonPrimaryDark, fontSize = 12.sp, modifier = Modifier.padding(end = 4.dp))
                                    Text(
                                        text = "Explore Learning Levels",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MaroonPrimaryDark
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "View beginner, intermediate, and advanced syllabus breakdown.",
                                    fontSize = 12.sp,
                                    color = MaroonPrimaryDark.copy(alpha = 0.85f)
                                )
                            }
                            Button(
                                onClick = { viewModel.navigateTo(Screen.LearningLevels) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimaryDark),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.border(1.dp, GoldAccent, RoundedCornerShape(8.dp))
                            ) {
                                Text("Syllabus", fontSize = 12.sp, color = GoldLight, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClassDetailCard(
    classItem: ClassItem,
    onEnrollClick: () -> Unit
) {
    ClassicalCard(
        borderColor = if (classItem.type == "Online") SuccessGreen.copy(alpha = 0.6f) else GoldAccent.copy(alpha = 0.6f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClassicalBadge(
                text = "${classItem.type.uppercase()} BATCH",
                containerColor = if (classItem.type == "Online") Color(0xFFE8F5E9) else GoldContainer,
                contentColor = if (classItem.type == "Online") SuccessGreen else MaroonPrimaryDark
            )
            Text(
                text = classItem.level,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaroonPrimary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = classItem.title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = TextCharcoal
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = classItem.description,
            fontSize = 13.sp,
            color = TextMuted,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(12.dp))
        TempleOrnamentalDivider(color = GoldAccent.copy(alpha = 0.4f))
        Spacer(modifier = Modifier.height(4.dp))

        // Schedule & Mentor
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, tint = MaroonPrimary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = classItem.schedule, fontSize = 12.sp, color = TextCharcoal, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = MaroonPrimary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = classItem.timing, fontSize = 12.sp, color = TextMuted)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Instructor", fontSize = 11.sp, color = TextSubtle)
                Text(text = classItem.teacher, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Key Inclusions:",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaroonPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        classItem.features.forEach { feature ->
            Row(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = if (classItem.type == "Online") SuccessGreen else GoldAccent,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = feature,
                    fontSize = 12.sp,
                    color = TextCharcoal
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onEnrollClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .border(1.dp, GoldAccent, RoundedCornerShape(10.dp))
                .testTag("class_enroll_${classItem.id}")
        ) {
            Text("Enroll Now", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

@Composable
fun LearningLevelsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val levels = viewModel.learningLevels.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MandalaWatermark(
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 80.dp, y = 60.dp),
            color = GoldAccent.copy(alpha = 0.05f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "✦", color = GoldAccent, fontSize = 14.sp, modifier = Modifier.padding(end = 6.dp))
                        Text(
                            text = "Structured Learning System",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimary
                        )
                    }
                    Text(
                        text = "From foundational Adavu footwork to full Margam solo performance readiness",
                        fontSize = 13.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }
            }

            items(levels) { level ->
                LearningLevelCard(level = level)
            }

            item {
                Button(
                    onClick = { viewModel.navigateTo(Screen.AdmissionForm) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(12.dp))
                ) {
                    Text("Enroll for Level Assessment", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun LearningLevelCard(level: LearningLevel) {
    ClassicalCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = level.levelName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MaroonPrimary
            )
            ClassicalBadge(text = level.durationApprox)
        }
        Text(
            text = level.subtitle,
            fontSize = 12.sp,
            color = GoldMuted,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = level.description,
            fontSize = 13.sp,
            color = TextCharcoal,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(10.dp))
        TempleOrnamentalDivider(color = GoldAccent.copy(alpha = 0.4f))
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Syllabus Curriculum:",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = MaroonPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        level.syllabusTopics.forEach { topic ->
            Row(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "❖", color = GoldAccent, fontSize = 10.sp, modifier = Modifier.padding(top = 2.dp, end = 6.dp))
                Text(text = topic, fontSize = 12.sp, color = TextCharcoal, lineHeight = 17.sp)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Key Focus:",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaroonPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            level.keyFocus.forEach { focus ->
                Surface(
                    color = CreamSurfaceVariant,
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
                ) {
                    Text(
                        text = focus,
                        fontSize = 11.sp,
                        color = TextCharcoal,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}
