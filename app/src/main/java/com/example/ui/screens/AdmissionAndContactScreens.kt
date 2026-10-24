package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AdmissionEnquiry
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.Screen

@Composable
fun AdmissionFormScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var studentName by remember { mutableStateOf("") }
    var parentName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var preferredClass by remember { mutableStateOf("Classical Bharatanatyam Foundation") }
    var mode by remember { mutableStateOf("Offline") }
    var experienceLevel by remember { mutableStateOf("Beginner") }
    var message by remember { mutableStateOf("") }

    var isSubmitted by remember { mutableStateOf(false) }

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
                    text = "Academy Admission & Enquiry",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Take your first step into sacred classical dance with Guru Hetal",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        if (isSubmitted) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GoldContainer)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(SuccessGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "Thank You for Contacting Nrutyashala!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            color = MaroonPrimaryDark,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Our administrative team and Guru Hetal will contact you shortly regarding batch allocation, timings, and uniform requirements.",
                            fontSize = 13.sp,
                            color = MaroonPrimaryDark,
                            textAlign = TextAlign.Center,
                            lineHeight = 19.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { isSubmitted = false },
                            colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary)
                        ) {
                            Text("Submit Another Enquiry", color = Color.White)
                        }
                    }
                }
            }
        } else {
            item {
                ClassicalCard {
                    Text(
                        text = "Student & Guardian Details",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Student Full Name *") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("enquiry_student_name"),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedTextField(
                            value = parentName,
                            onValueChange = { parentName = it },
                            label = { Text("Parent / Guardian Name") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        OutlinedTextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text("Age *") },
                            modifier = Modifier.width(90.dp),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Mobile / WhatsApp Number *") },
                        placeholder = { Text("+91 98765 43210") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("enquiry_phone"),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("City / Country") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Preferred Training Mode", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaroonPrimary)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FilterChip(
                            selected = mode == "Offline",
                            onClick = { mode = "Offline" },
                            label = { Text("Offline Physical Studio") },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaroonPrimary, selectedLabelColor = Color.White)
                        )
                        FilterChip(
                            selected = mode == "Online",
                            onClick = { mode = "Online" },
                            label = { Text("Global Online Live") },
                            colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaroonPrimary, selectedLabelColor = Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Current Experience Level", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaroonPrimary)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Beginner", "Intermediate", "Advanced").forEach { lvl ->
                            FilterChip(
                                selected = experienceLevel == lvl,
                                onClick = { experienceLevel = lvl },
                                label = { Text(lvl) },
                                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaroonPrimary, selectedLabelColor = Color.White)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("Notes or Dance Background (Optional)") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (studentName.isNotBlank() && phone.isNotBlank()) {
                                viewModel.submitEnquiry(
                                    AdmissionEnquiry(
                                        id = "enq_${System.currentTimeMillis()}",
                                        studentName = studentName,
                                        parentName = parentName.ifBlank { "N/A" },
                                        age = age.ifBlank { "N/A" },
                                        phone = phone,
                                        email = email.ifBlank { "N/A" },
                                        city = city.ifBlank { "Gujarat" },
                                        preferredClass = preferredClass,
                                        mode = mode,
                                        experienceLevel = experienceLevel,
                                        message = message.ifBlank { "Interested in classical Bharatanatyam classes." },
                                        dateSubmitted = "Oct 2026"
                                    )
                                )
                                isSubmitted = true
                            } else {
                                viewModel.showMessage("Please fill in Student Name and Phone Number.")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("submit_enquiry_btn")
                    ) {
                        Text("Submit Admission Enquiry", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ContactUsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val studioInfo = viewModel.studioInfo.value
    val context = LocalContext.current

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
                    text = "Contact Nrutyashala",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Reach out for class admissions, workshops, or solo performance inquiries",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        // Quick Connect Buttons Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { dialPhoneNumber(context, studioInfo.phone) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = GoldLight, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Call Now", fontSize = 12.sp, color = Color.White)
                }

                Button(
                    onClick = { launchWhatsApp(context, studioInfo.whatsappNumber, studioInfo.whatsappDefaultMessage) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("WhatsApp", fontSize = 12.sp, color = Color.White)
                }
            }
        }

        // Contact Information Cards
        item {
            ClassicalCard {
                ContactRowItem(
                    icon = Icons.Default.Place,
                    title = "Dance Studio Address",
                    detail = studioInfo.address,
                    actionText = "Get Directions",
                    onAction = { openMapLocation(context, studioInfo.address) }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = CardBorder)
                ContactRowItem(
                    icon = Icons.Default.Phone,
                    title = "Studio Phone & Inquiries",
                    detail = studioInfo.phone,
                    actionText = "Dial",
                    onAction = { dialPhoneNumber(context, studioInfo.phone) }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = CardBorder)
                ContactRowItem(
                    icon = Icons.Default.Email,
                    title = "Official Email",
                    detail = studioInfo.email,
                    actionText = "Send Email",
                    onAction = { sendEmailIntent(context, studioInfo.email, "Inquiry: Nrutyashala Dance Academy") }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = CardBorder)
                ContactRowItem(
                    icon = Icons.Default.Language,
                    title = "Official Website",
                    detail = studioInfo.websiteUrl,
                    actionText = "Open Web",
                    onAction = {
                        try {
                            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(studioInfo.websiteUrl))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            viewModel.showMessage("Could not open browser.")
                        }
                    }
                )
            }
        }

        // Enquiry Form Shortcut
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldAccent, RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = GoldContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Prefer Written Enquiry?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimaryDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Submit our streamlined admission and batch assessment form to receive detailed curriculum brochures.",
                        fontSize = 12.sp,
                        color = MaroonPrimaryDark.copy(alpha = 0.9f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { viewModel.navigateTo(Screen.AdmissionForm) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimaryDark),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open Admission Form", color = GoldLight, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ContactRowItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    detail: String,
    actionText: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(GoldContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = MaroonPrimary, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = title, fontSize = 11.sp, color = TextSubtle, fontWeight = FontWeight.Medium)
                Text(text = detail, fontSize = 13.sp, color = TextCharcoal, fontWeight = FontWeight.SemiBold)
            }
        }
        TextButton(onClick = onAction) {
            Text(text = actionText, color = MaroonPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
