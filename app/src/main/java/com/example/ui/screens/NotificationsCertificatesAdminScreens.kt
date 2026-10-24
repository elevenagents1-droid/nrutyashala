package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.components.ClassicalBadge
import com.example.ui.components.ClassicalCard
import com.example.ui.components.TempleOrnamentalDivider
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel

@Composable
fun NotificationsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val notifications = viewModel.notifications.value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Announcements & Notices",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Official updates on classes, examinations, and festival rehearsals",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        if (notifications.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CreamSurfaceVariant)
                ) {
                    Text(
                        text = "No current notices.",
                        modifier = Modifier.padding(24.dp),
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            items(notifications) { notif ->
                NotificationCard(notification = notif)
            }
        }
    }
}

@Composable
fun NotificationCard(notification: AppNotification) {
    ClassicalCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ClassicalBadge(
                text = notification.category,
                containerColor = when (notification.category) {
                    "Exam" -> DeepTerracotta.copy(alpha = 0.15f)
                    "Event" -> GoldContainer
                    else -> CreamSurfaceVariant
                },
                contentColor = when (notification.category) {
                    "Exam" -> DeepTerracotta
                    else -> MaroonPrimaryDark
                }
            )
            Text(text = notification.timestamp, fontSize = 11.sp, color = TextSubtle)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = notification.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = TextCharcoal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = notification.message,
            fontSize = 13.sp,
            color = TextMuted,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun CertificatesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val certs = viewModel.studentCertificates.value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Academy Certificates & Honors",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Text(
                    text = "Accredited board certificates & studio performance credentials",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        items(certs) { cert ->
            CertificateItemCard(
                certificate = cert,
                onViewCertificate = { viewModel.openCertificateViewer(cert) }
            )
        }
    }
}

@Composable
fun AdminPanelScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isAdmin = viewModel.isAdminLoggedIn.value
    var pinInput by remember { mutableStateOf("") }

    if (!isAdmin) {
        // Admin PIN Gate
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaroonPrimaryDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AdminPanelSettings, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(36.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nrutyashala Admin Portal",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MaroonPrimary
            )
            Text(
                text = "Enter administration authorization passcode to manage batches, attendance, events, and admissions.",
                fontSize = 13.sp,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            ClassicalCard {
                OutlinedTextField(
                    value = pinInput,
                    onValueChange = { pinInput = it },
                    label = { Text("Admin PIN (e.g. 1234)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_pin_input"),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.loginAdmin(pinInput) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("admin_login_submit_btn")
                ) {
                    Text("Unlock Admin Dashboard", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    } else {
        AdminDashboardContent(viewModel = viewModel, modifier = modifier)
    }
}

@Composable
fun AdminDashboardContent(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val enquiries = viewModel.enquiries.value
    val studioInfo = viewModel.studioInfo.value
    val currentStudent = viewModel.currentStudent.value

    var activeTab by remember { mutableStateOf(0) } // 0=Enquiries, 1=Attendance & Student, 2=Broadcast, 3=Settings

    var notifTitle by remember { mutableStateOf("") }
    var notifMessage by remember { mutableStateOf("") }

    var studioPhone by remember { mutableStateOf(studioInfo.phone) }
    var studioWhatsapp by remember { mutableStateOf(studioInfo.whatsappNumber) }
    var studioEmail by remember { mutableStateOf(studioInfo.email) }
    var studioAddress by remember { mutableStateOf(studioInfo.address) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Academy Control Center",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimary
                    )
                    Text(text = "Manage studio operations & admissions", fontSize = 12.sp, color = TextMuted)
                }
                OutlinedButton(
                    onClick = { viewModel.logoutAdmin() },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DeepTerracotta),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DeepTerracotta),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Lock Admin", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            TabRow(
                selectedTabIndex = activeTab,
                containerColor = CreamSurfaceVariant,
                contentColor = MaroonPrimary
            ) {
                Tab(selected = activeTab == 0, onClick = { activeTab = 0 }, text = { Text("Enquiries (${enquiries.size})", fontSize = 12.sp) })
                Tab(selected = activeTab == 1, onClick = { activeTab = 1 }, text = { Text("Students", fontSize = 12.sp) })
                Tab(selected = activeTab == 2, onClick = { activeTab = 2 }, text = { Text("Notices", fontSize = 12.sp) })
                Tab(selected = activeTab == 3, onClick = { activeTab = 3 }, text = { Text("Contacts", fontSize = 12.sp) })
            }
        }

        when (activeTab) {
            0 -> {
                // Enquiries
                items(enquiries) { enq ->
                    ClassicalCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = enq.studentName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
                            ClassicalBadge(
                                text = enq.status,
                                containerColor = if (enq.status == "New") GoldContainer else Color(0xFFE8F5E9),
                                contentColor = if (enq.status == "New") MaroonPrimaryDark else SuccessGreen
                            )
                        }
                        Text(text = "Parent: ${enq.parentName} • Age: ${enq.age} • Phone: ${enq.phone}", fontSize = 12.sp, color = TextCharcoal)
                        Text(text = "Class: ${enq.preferredClass} (${enq.mode}) • Level: ${enq.experienceLevel}", fontSize = 12.sp, color = TextMuted)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "“${enq.message}”", fontSize = 12.sp, color = TextCharcoal, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = { viewModel.updateEnquiryStatus(enq.id, "Contacted") },
                                colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(34.dp)
                            ) {
                                Text("Mark Contacted", fontSize = 11.sp, color = Color.White)
                            }
                            Button(
                                onClick = { viewModel.updateEnquiryStatus(enq.id, "Enrolled") },
                                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(34.dp)
                            ) {
                                Text("Enroll", fontSize = 11.sp, color = Color.White)
                            }
                        }
                    }
                }
            }
            1 -> {
                // Students Attendance & Progress Control
                item {
                    ClassicalCard {
                        Text(text = "Manage Sample Student Attendance", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
                        Text(text = "Current: ${currentStudent?.name ?: "No student logged in"}", fontSize = 12.sp, color = TextMuted)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = { viewModel.markAttendance(attended = 1, total = 1) },
                                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Mark Present (+1)", fontSize = 12.sp, color = Color.White)
                            }
                            Button(
                                onClick = { viewModel.markAttendance(attended = 0, total = 1) },
                                colors = ButtonDefaults.buttonColors(containerColor = DeepTerracotta),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Mark Absent", fontSize = 12.sp, color = Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Update Skill Level Progress (%):", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaroonPrimary)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { viewModel.updateStudentProgress(adavus = 95, theory = 90, practical = 95) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Advance Level (95%)", fontSize = 11.sp, color = Color.White)
                            }
                        }
                    }
                }
            }
            2 -> {
                // Broadcast Notice
                item {
                    ClassicalCard {
                        Text(text = "Broadcast Student Announcement", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = notifTitle,
                            onValueChange = { notifTitle = it },
                            label = { Text("Notice Title") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = notifMessage,
                            onValueChange = { notifMessage = it },
                            label = { Text("Announcement Details") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            shape = RoundedCornerShape(8.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                if (notifTitle.isNotBlank() && notifMessage.isNotBlank()) {
                                    viewModel.sendNotification(notifTitle, notifMessage, "General")
                                    notifTitle = ""
                                    notifMessage = ""
                                } else {
                                    viewModel.showMessage("Please fill Title and Details.")
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Send to All Students", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            3 -> {
                // Studio Contact Settings
                item {
                    ClassicalCard {
                        Text(text = "Studio Contact Details (Website / WhatsApp)", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = studioPhone,
                            onValueChange = { studioPhone = it },
                            label = { Text("Phone Number") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = studioWhatsapp,
                            onValueChange = { studioWhatsapp = it },
                            label = { Text("WhatsApp Number") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = studioEmail,
                            onValueChange = { studioEmail = it },
                            label = { Text("Email Address") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = studioAddress,
                            onValueChange = { studioAddress = it },
                            label = { Text("Studio Address") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = {
                                viewModel.updateStudioContact(studioPhone, studioWhatsapp, studioEmail, studioAddress)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Save Studio Details", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
