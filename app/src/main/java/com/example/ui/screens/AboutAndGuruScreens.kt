package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
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
fun AboutUsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val pillars = listOf(
        AboutPillar("Traditional Bharatanatyam", "Faithful adherence to the revered Pandanallur / Kalakshetra traditions and the sacred principles of Natyashastra.", Icons.Default.SelfImprovement),
        AboutPillar("Artistic Expression (Abhinaya)", "Cultivating deep emotional intelligence, facial expressiveness, and graceful hand gestures (Mudras).", Icons.Default.Face),
        AboutPillar("Cultural Heritage & Discipline", "Instilling sacred respect for ancient Indian classical arts, guru-shishya parampara, and daily dedication.", Icons.Default.AccountBalance),
        AboutPillar("Confidence & Stage Poise", "Transforming students through regular recitals, stage rehearsals, and rhythmic poise.", Icons.Default.Stars),
        AboutPillar("Board Exam Preparation", "Affiliated curriculum aligned with Akhil Bhartiya Gandharva Mahavidyalaya board certifications.", Icons.Default.MenuBook),
        AboutPillar("Online & Offline Learning", "Bridging geographical boundaries with flexible live interactive sessions alongside studio training.", Icons.Default.Language)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Decorative background mandala
        MandalaWatermark(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.BottomStart)
                .offset(x = -80.dp, y = 80.dp),
            color = GoldAccent.copy(alpha = 0.05f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Header Card with Traditional Toran and Gold Motifs
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            2.dp,
                            Brush.linearGradient(listOf(GoldAccent, GoldLight, GoldAccent)),
                            RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaroonPrimaryDark),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CardCornerMotifs(color = GoldAccent.copy(alpha = 0.7f))

                        Column(
                            modifier = Modifier.padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "✦", color = GoldAccent, fontSize = 11.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "ABOUT NRUTYASHALA",
                                    color = GoldLight,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "✦", color = GoldAccent, fontSize = 11.sp)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "The Dance Studio",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TempleOrnamentalDivider(color = GoldAccent)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Nrutyashala is an esteemed Indian classical dance institution dedicated to the preservation, dissemination, and celebration of Bharatanatyam. Based on ancient scriptures and handed down through unbroken traditions, our studio provides a sacred space where dancers of all ages discover poise, rhythm, and devotion.",
                                color = Color.White.copy(alpha = 0.95f),
                                fontSize = 13.sp,
                                lineHeight = 22.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Decorative Toran Arch
            item {
                TempleToranArch(color = GoldAccent)
            }

            // Philosophy & Mission
            item {
                ClassicalCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "❖", color = GoldAccent, fontSize = 13.sp, modifier = Modifier.padding(end = 6.dp))
                        Text(
                            text = "Our Artistic Philosophy",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Bharatanatyam is not mere movement; it is visual poetry, a dynamic yoga of the soul, and an intimate dialogue between dancer and the divine. At Nrutyashala, we believe in nurturing the whole artist — sharpening physical stamina (Nritta), deepening emotional empathy (Abhinaya), and cultivating intellectual grounding in dance history and philosophy (Natya).",
                        fontSize = 13.sp,
                        lineHeight = 21.sp,
                        color = TextCharcoal
                    )
                }
            }

            // Core Pillars Grid Title
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "✦", color = GoldAccent, fontSize = 12.sp, modifier = Modifier.padding(end = 6.dp))
                    Text(
                        text = "Pillars of Academy Training",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimary
                    )
                }
            }

            // Pillars with Gold Borders & Indian Motifs
            items(pillars) { pillar ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.2.dp, GoldAccent.copy(alpha = 0.45f), RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CardCornerMotifs(color = GoldAccent.copy(alpha = 0.35f))

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(listOf(GoldContainer, WarmSand))
                                    )
                                    .border(1.2.dp, GoldAccent, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = pillar.icon,
                                    contentDescription = pillar.title,
                                    tint = MaroonPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = pillar.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = TextCharcoal
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = pillar.desc,
                                    fontSize = 12.sp,
                                    color = TextMuted,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
            }

            // Admission Callout Button with golden border
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.navigateTo(Screen.AdmissionForm) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(12.dp))
                        .testTag("about_us_join_btn")
                ) {
                    Icon(imageVector = Icons.Default.Stars, contentDescription = null, tint = GoldAccent)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Begin Your Classical Dance Journey",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

private data class AboutPillar(
    val title: String,
    val desc: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun MeetGuruScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val studioInfo = viewModel.studioInfo.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Decorative background mandala
        MandalaWatermark(
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = 40.dp),
            color = GoldAccent.copy(alpha = 0.05f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Teacher Profile Header with Decorative Arch & Traditional Border
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            2.dp,
                            Brush.linearGradient(listOf(GoldAccent, GoldLight, GoldAccent)),
                            RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CardCornerMotifs(color = GoldAccent.copy(alpha = 0.6f))

                        Column(
                            modifier = Modifier.padding(22.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(id = R.drawable.img_guru_hetal),
                                    contentDescription = "Guru Hetal Founder Teacher",
                                    modifier = Modifier
                                        .size(135.dp)
                                        .clip(CircleShape)
                                        .border(3.dp, GoldAccent, CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                // Lotus badge overlay
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(MaroonPrimaryDark)
                                        .border(1.5.dp, GoldAccent, CircleShape)
                                        .align(Alignment.BottomEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "🕉", fontSize = 13.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Guru Hetal",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = MaroonPrimary
                            )
                            Text(
                                text = "Founder, Artistic Director & Principal Guru",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextMuted
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TempleOrnamentalDivider(color = GoldAccent.copy(alpha = 0.7f))
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                ClassicalBadge(text = "B.A. Bharatanatyam")
                                ClassicalBadge(text = "Visharad")
                                ClassicalBadge(text = "Gandharva Certified")
                            }
                        }
                    }
                }
            }

            // Credentials & Qualifications
            item {
                ClassicalCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = GoldMuted,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Qualifications & Accreditations",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    studioInfo.founderQualifications.forEach { qual ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "❖",
                                color = GoldAccent,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = qual,
                                fontSize = 13.sp,
                                color = TextCharcoal,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }
            }

            // Biography & Artistic Heritage
            item {
                ClassicalCard {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "✦", color = GoldAccent, fontSize = 13.sp, modifier = Modifier.padding(end = 6.dp))
                        Text(
                            text = "Artistic Journey & Experience",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Guru Hetal is an accomplished Bharatanatyam exponent whose dance lineage combines rigorous academic study with authentic stage execution. Having accomplished her Bachelor of Arts in Bharatanatyam alongside prestigious Visharad honors, she underwent intensive Arangetram training under eminent gurus.\n\nOver years of dedicated performance and teaching, Guru Hetal has represented the classical tradition at celebrated national and international cultural forums, including the Vibrant Gujarat Summit. As a registered teacher with the Akhil Bhartiya Gandharva Mahavidyalaya, she has guided scores of disciples through foundational exams up to their solo Arangetram debuts.",
                        fontSize = 13.sp,
                        lineHeight = 21.sp,
                        color = TextCharcoal
                    )
                }
            }

            // Teaching Philosophy
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GoldContainer)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CardCornerMotifs(color = GoldAccent.copy(alpha = 0.5f))

                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "❖", color = MaroonPrimaryDark, fontSize = 12.sp, modifier = Modifier.padding(end = 6.dp))
                                Text(
                                    text = "Teaching Philosophy",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    color = MaroonPrimaryDark
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "“Every student holds an innate spark of Natya. My mission is to instill authentic discipline without fear, cultivate joy in precision, and empower every dancer with the spiritual poise that stays with them throughout life.”",
                                fontSize = 13.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                lineHeight = 20.sp,
                                color = MaroonPrimaryDark
                            )
                        }
                    }
                }
            }

            // Direct CTA
            item {
                Button(
                    onClick = { viewModel.navigateTo(Screen.AdmissionForm) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .border(1.2.dp, GoldAccent, RoundedCornerShape(12.dp))
                        .testTag("guru_learn_with_btn")
                ) {
                    Text(
                        text = "Learn Under Guru Hetal",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
