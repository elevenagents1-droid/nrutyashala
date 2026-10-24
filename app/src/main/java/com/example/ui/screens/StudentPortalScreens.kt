package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
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
import com.example.model.Certificate
import com.example.model.StudentUser
import com.example.ui.components.ClassicalBadge
import com.example.ui.components.ClassicalCard
import com.example.ui.components.TempleOrnamentalDivider
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.Screen

@Composable
fun StudentPortalScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentStudent = viewModel.currentStudent.value

    if (currentStudent == null) {
        StudentLoginView(
            onLogin = { name, sid -> viewModel.loginStudent(name, sid) },
            modifier = modifier
        )
    } else {
        StudentDashboardView(
            student = currentStudent,
            viewModel = viewModel,
            modifier = modifier
        )
    }
}

@Composable
fun StudentLoginView(
    onLogin: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var studentName by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(GoldContainer)
                    .border(1.5.dp, GoldAccent, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = MaroonPrimary,
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nrutyashala Student Portal",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = MaroonPrimary
            )
            Text(
                text = "Access your attendance, syllabus progress, and official certificates",
                fontSize = 13.sp,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            ClassicalCard {
                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Student Name") },
                    placeholder = { Text("e.g. Aaradhya Sharma") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaroonPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("login_name_input"),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("Student ID / Roll No.") },
                    placeholder = { Text("e.g. NRUT-2024-042") },
                    leadingIcon = { Icon(Icons.Default.Badge, contentDescription = null, tint = MaroonPrimary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("login_id_input"),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Registered Email") },
                    placeholder = { Text("student@example.com") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MaroonPrimary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    placeholder = { Text("••••••••") },
                    leadingIcon = { Icon(Icons.Default.Password, contentDescription = null, tint = MaroonPrimary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        onLogin(
                            if (studentName.isBlank()) "Aaradhya Sharma" else studentName,
                            if (studentId.isBlank()) "NRUT-2024-042" else studentId
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("login_submit_btn")
                ) {
                    Text("Secure Student Login", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Tip: Tap Secure Login directly for instant Demo Student Dashboard",
                    fontSize = 11.sp,
                    color = TextSubtle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun StudentDashboardView(
    student: StudentUser,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val certificates = viewModel.studentCertificates.value
    val attendancePct = ((student.attendedClasses.toFloat() / student.totalClasses.toFloat()) * 100).toInt()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcome Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, GoldAccent, RoundedCornerShape(18.dp)),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaroonPrimaryDark)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Welcome, ${student.name}",
                                color = GoldLight,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                            Text(
                                text = "Student ID: ${student.studentId} • Batch: ${student.batch}",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                        }
                        IconButton(
                            onClick = { viewModel.logoutStudent() },
                            modifier = Modifier.testTag("student_logout_btn")
                        ) {
                            Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout", tint = GoldLight)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ClassicalBadge(
                            text = student.currentLevel,
                            containerColor = MaroonPrimary,
                            contentColor = GoldLight
                        )
                        ClassicalBadge(
                            text = "${student.mode} Mode",
                            containerColor = MaroonPrimary,
                            contentColor = Color.White
                        )
                    }
                }
            }
        }

        // Attendance Section
        item {
            ClassicalCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Attendance Record",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = MaroonPrimary
                    )
                    Text(
                        text = "$attendancePct%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (attendancePct >= 80) SuccessGreen else DeepTerracotta
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { attendancePct / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = GoldAccent,
                    trackColor = WarmSand
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Total Classes", fontSize = 11.sp, color = TextMuted)
                        Text(text = "${student.totalClasses}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextCharcoal)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Attended", fontSize = 11.sp, color = SuccessGreen)
                        Text(text = "${student.attendedClasses}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Absent", fontSize = 11.sp, color = DeepTerracotta)
                        Text(text = "${student.totalClasses - student.attendedClasses}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DeepTerracotta)
                    }
                }
            }
        }

        // Course Progress Tracking
        item {
            ClassicalCard {
                Text(
                    text = "Syllabus Mastery Progress",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    color = MaroonPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))

                ProgressSkillItem(label = "Adavus Footwork & Posture (Araimandi)", pct = student.adavusProgressPct)
                ProgressSkillItem(label = "Natyashastra Theory & Hastas", pct = student.theoryProgressPct)
                ProgressSkillItem(label = "Practical Abhinaya & Rhythmic Talam", pct = student.practicalProgressPct)

                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GoldContainer, RoundedCornerShape(8.dp))
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = MaroonPrimary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Examination Status: ${student.examReadiness}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaroonPrimaryDark
                    )
                }
            }
        }

        // Student Certificates
        item {
            SectionHeader(
                title = "My Certificates",
                subtitle = "Official honors & board diplomas",
                actionText = "All",
                onActionClick = { viewModel.navigateTo(Screen.Certificates) }
            )
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                certificates.forEach { cert ->
                    CertificateItemCard(
                        certificate = cert,
                        onViewCertificate = { viewModel.openCertificateViewer(cert) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressSkillItem(label: String, pct: Int) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 12.sp, color = TextCharcoal)
            Text(text = "$pct%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaroonPrimary)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { pct / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaroonPrimary,
            trackColor = WarmSand
        )
    }
}

@Composable
fun CertificateItemCard(
    certificate: Certificate,
    onViewCertificate: () -> Unit
) {
    ClassicalCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.WorkspacePremium, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = certificate.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = TextCharcoal
                    )
                    Text(
                        text = "ID: ${certificate.certificateId} • Date: ${certificate.issuedDate}",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Awarded to ${certificate.studentName} for ${certificate.courseOrExam} (${certificate.grade}).", fontSize = 12.sp, color = TextCharcoal)
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = onViewCertificate,
                colors = ButtonDefaults.buttonColors(containerColor = MaroonPrimary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
            ) {
                Text("View Certificate", fontSize = 11.sp, color = Color.White)
            }
            OutlinedButton(
                onClick = onViewCertificate,
                border = androidx.compose.foundation.BorderStroke(1.dp, GoldAccent),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
            ) {
                Text("Download PDF", fontSize = 11.sp, color = MaroonPrimary)
            }
        }
    }
}
