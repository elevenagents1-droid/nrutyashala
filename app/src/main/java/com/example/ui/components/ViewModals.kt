package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.model.Certificate
import com.example.model.GalleryItem
import com.example.ui.theme.*

@Composable
fun FullscreenGalleryDialog(
    item: GalleryItem?,
    onDismiss: () -> Unit
) {
    if (item == null) return

    val drawableId = when (item.imageResName) {
        "img_guru_hetal" -> R.drawable.img_guru_hetal
        "img_dance_class" -> R.drawable.img_dance_class
        "img_hero_banner" -> R.drawable.img_hero_banner
        else -> R.drawable.img_performance_event
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.92f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Header Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClassicalBadge(
                        text = item.category,
                        containerColor = MaroonPrimary,
                        contentColor = GoldLight
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }

                // Center Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.5.dp, GoldAccent.copy(alpha = 0.6f), RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit
                    )
                }

                // Bottom Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = item.title,
                        color = GoldLight,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.description,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CertificateViewerDialog(
    certificate: Certificate?,
    onDismiss: () -> Unit
) {
    if (certificate == null) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.85f))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            // Elegant Classical Traditional Certificate Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(3.dp, GoldAccent, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CreamBackground)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaroonPrimary)
                        }
                    }

                    // Certificate Seal Header
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(MaroonPrimaryDark)
                            .border(2.dp, GoldAccent, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(30.dp))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "NRUTYASHALA – THE DANCE STUDIO",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaroonPrimary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Certificate of Achievement",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimaryDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    TempleOrnamentalDivider(color = GoldAccent)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "This is proudly presented to",
                        fontSize = 12.sp,
                        color = TextMuted,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = certificate.studentName,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "for outstanding dedication and mastery in",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                    Text(
                        text = certificate.courseOrExam,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextCharcoal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Grade: ${certificate.grade}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SuccessGreen
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = CardBorder)
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(text = "Date: ${certificate.issuedDate}", fontSize = 11.sp, color = TextSubtle)
                            Text(text = "ID: ${certificate.certificateId}", fontSize = 10.sp, color = TextSubtle)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Guru Hetal", fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = MaroonPrimary)
                            Text(text = "Artistic Director", fontSize = 10.sp, color = TextMuted)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close Certificate", color = Color.White)
                    }
                }
            }
        }
    }
}
