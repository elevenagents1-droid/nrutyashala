package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

/**
 * Traditional Indian Kolam/Mandala decorative divider with central diamond and lotus petal motifs
 */
@Composable
fun TempleOrnamentalDivider(
    modifier: Modifier = Modifier,
    color: Color = GoldAccent.copy(alpha = 0.75f)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.5.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, color)
                    )
                )
        )
        Spacer(modifier = Modifier.width(6.dp))
        // Ghungroo bell / flower bud dot
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(5.dp))
        // Temple Arch / Kalash diamond motif
        Canvas(modifier = Modifier.size(16.dp)) {
            val w = size.width
            val h = size.height
            val path = Path().apply {
                moveTo(w / 2f, 0f)
                lineTo(w, h / 2f)
                lineTo(w / 2f, h)
                lineTo(0f, h / 2f)
                close()
            }
            drawPath(path, color = color)
            // Inner hollow dot
            drawCircle(color = MaroonPrimaryDark, radius = 2.5f, center = Offset(w / 2f, h / 2f))
        }
        Spacer(modifier = Modifier.width(5.dp))
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.5.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(color, Color.Transparent)
                    )
                )
        )
    }
}

/**
 * Traditional Toran / Temple Gopuram Archway Border at the top of sections
 */
@Composable
fun TempleToranArch(
    modifier: Modifier = Modifier,
    color: Color = GoldAccent
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
    ) {
        val step = 24.dp.toPx()
        val count = (size.width / step).toInt() + 1
        val strokeWidth = 1.5.dp.toPx()

        for (i in 0 until count) {
            val startX = i * step
            val path = Path().apply {
                moveTo(startX, 0f)
                quadraticTo(startX + step / 2f, size.height, startX + step, 0f)
            }
            drawPath(path, color = color.copy(alpha = 0.5f), style = Stroke(width = strokeWidth))
            // Small hanging ghungroo pearl
            drawCircle(
                color = color.copy(alpha = 0.8f),
                radius = 2.dp.toPx(),
                center = Offset(startX + step / 2f, size.height)
            )
        }
    }
}

/**
 * Sacred Rangoli / Mandala Watermark drawn in the background
 */
@Composable
fun MandalaWatermark(
    modifier: Modifier = Modifier,
    color: Color = GoldAccent.copy(alpha = 0.08f)
) {
    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val maxRadius = size.minDimension / 2f

        // Concentric circles
        drawCircle(color = color, radius = maxRadius * 0.95f, center = center, style = Stroke(1.5f))
        drawCircle(color = color, radius = maxRadius * 0.75f, center = center, style = Stroke(1f))
        drawCircle(color = color, radius = maxRadius * 0.5f, center = center, style = Stroke(1.5f))
        drawCircle(color = color, radius = maxRadius * 0.25f, center = center, style = Stroke(1f))

        // 8 Petals
        val petals = 8
        for (i in 0 until petals) {
            val angle = (i * 2 * Math.PI / petals).toFloat()
            val x1 = center.x + cos(angle) * (maxRadius * 0.75f)
            val y1 = center.y + sin(angle) * (maxRadius * 0.75f)
            val x2 = center.x + cos(angle) * (maxRadius * 0.25f)
            val y2 = center.y + sin(angle) * (maxRadius * 0.25f)
            drawLine(color = color, start = Offset(x2, y2), end = Offset(x1, y1), strokeWidth = 1f)

            // Small diamond on each petal tip
            drawCircle(color = color, radius = 3f, center = Offset(x1, y1))
        }
    }
}

/**
 * Decorative Indian Corner Motif for Cards
 */
@Composable
fun CardCornerMotifs(
    color: Color = GoldAccent.copy(alpha = 0.6f)
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val arm = 14.dp.toPx()
        val inset = 6.dp.toPx()
        val sw = 1.5.dp.toPx()

        // Top-Left corner motif
        drawLine(color = color, start = Offset(inset, inset), end = Offset(inset + arm, inset), strokeWidth = sw)
        drawLine(color = color, start = Offset(inset, inset), end = Offset(inset, inset + arm), strokeWidth = sw)
        drawCircle(color = color, radius = 2.dp.toPx(), center = Offset(inset, inset))

        // Top-Right corner motif
        val rX = size.width - inset
        drawLine(color = color, start = Offset(rX, inset), end = Offset(rX - arm, inset), strokeWidth = sw)
        drawLine(color = color, start = Offset(rX, inset), end = Offset(rX, inset + arm), strokeWidth = sw)
        drawCircle(color = color, radius = 2.dp.toPx(), center = Offset(rX, inset))

        // Bottom-Left corner motif
        val bY = size.height - inset
        drawLine(color = color, start = Offset(inset, bY), end = Offset(inset + arm, bY), strokeWidth = sw)
        drawLine(color = color, start = Offset(inset, bY), end = Offset(inset, bY - arm), strokeWidth = sw)
        drawCircle(color = color, radius = 2.dp.toPx(), center = Offset(inset, bY))

        // Bottom-Right corner motif
        drawLine(color = color, start = Offset(rX, bY), end = Offset(rX - arm, bY), strokeWidth = sw)
        drawLine(color = color, start = Offset(rX, bY), end = Offset(rX, bY - arm), strokeWidth = sw)
        drawCircle(color = color, radius = 2.dp.toPx(), center = Offset(rX, bY))
    }
}

/**
 * Floating WhatsApp action button with custom message launcher & gold ring pulse
 */
@Composable
fun FloatingWhatsAppButton(
    phoneNumber: String,
    defaultMessage: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = modifier.size(62.dp),
        contentAlignment = Alignment.Center
    ) {
        // Subtle gold glow ring behind
        Box(
            modifier = Modifier
                .size((56 * pulseScale).dp)
                .clip(CircleShape)
                .background(GoldAccent.copy(alpha = 0.35f))
        )

        FloatingActionButton(
            onClick = {
                launchWhatsApp(context, phoneNumber, defaultMessage)
            },
            containerColor = Color(0xFF25D366),
            contentColor = Color.White,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
            modifier = Modifier
                .testTag("floating_whatsapp_btn")
                .size(56.dp)
                .border(2.dp, GoldLight, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Chat,
                contentDescription = "Contact on WhatsApp",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

fun launchWhatsApp(context: Context, phone: String, message: String) {
    try {
        val cleanNumber = phone.replace("+", "").replace(" ", "").replace("-", "")
        val encodedMsg = Uri.encode(message)
        val url = "https://api.whatsapp.com/send?phone=$cleanNumber&text=$encodedMsg"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun dialPhoneNumber(context: Context, phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not launch dialer: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun openMapLocation(context: Context, location: String) {
    try {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        context.startActivity(mapIntent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open map: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun sendEmailIntent(context: Context, email: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not launch email client: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

/**
 * Classical Gold Bordered Card Container with Decorative Indian Temple Corner Accents
 */
@Composable
fun ClassicalCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = GoldAccent.copy(alpha = 0.5f),
    showCornerMotifs: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.2.dp, borderColor, RoundedCornerShape(16.dp))
            .shadow(3.dp, RoundedCornerShape(16.dp), spotColor = GoldMuted),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (showCornerMotifs) {
                CardCornerMotifs(color = borderColor.copy(alpha = 0.7f))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                content()
            }
        }
    }
}

/**
 * Tag / Badge for Traditional Class or Achievement Category with golden border
 */
@Composable
fun ClassicalBadge(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = GoldContainer,
    contentColor: Color = MaroonPrimaryDark
) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.2.dp, GoldAccent.copy(alpha = 0.7f)),
        shadowElevation = 1.dp,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = "❖",
                color = GoldMuted,
                fontSize = 9.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = text,
                color = contentColor,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }
    }
}
