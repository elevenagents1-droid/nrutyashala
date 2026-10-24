package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.StudioInfo
import com.example.ui.components.CertificateViewerDialog
import com.example.ui.components.FloatingWhatsAppButton
import com.example.ui.components.FullscreenGalleryDialog
import com.example.ui.components.MandalaWatermark
import com.example.ui.components.TempleOrnamentalDivider
import com.example.ui.components.TempleToranArch
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NrutyashalaTheme {
                MainAppRoot()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppRoot(viewModel: MainViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()
    val studioInfo by viewModel.studioInfo.collectAsState()
    val activeGalleryItem by viewModel.activeGalleryItem.collectAsState()
    val activeCertificate by viewModel.activeCertificate.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearSnackbar()
        }
    }

    // Hardware back navigation handler
    BackHandler(enabled = currentScreen != Screen.Home) {
        viewModel.navigateBack()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = CreamBackground,
                drawerContentColor = TextCharcoal,
                modifier = Modifier.width(310.dp)
            ) {
                DrawerMenuContent(
                    studioInfo = studioInfo,
                    currentScreen = currentScreen,
                    onNavigate = { screen ->
                        viewModel.navigateTo(screen)
                        coroutineScope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "✦", color = GoldAccent, fontSize = 11.sp, modifier = Modifier.padding(end = 4.dp))
                                    Text(
                                        text = "Nrutyashala",
                                        fontSize = 19.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = GoldLight,
                                        letterSpacing = 0.5.sp
                                    )
                                    Text(text = "✦", color = GoldAccent, fontSize = 11.sp, modifier = Modifier.padding(start = 4.dp))
                                }
                                Text(
                                    text = currentScreen.title.uppercase(),
                                    fontSize = 10.sp,
                                    color = GoldAccent,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                        },
                        navigationIcon = {
                            if (currentScreen == Screen.Home) {
                                IconButton(
                                    onClick = { coroutineScope.launch { drawerState.open() } },
                                    modifier = Modifier.testTag("nav_drawer_open_btn")
                                ) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = GoldLight)
                                }
                            } else {
                                IconButton(
                                    onClick = { viewModel.navigateBack() },
                                    modifier = Modifier.testTag("nav_back_btn")
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = GoldLight)
                                }
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = { viewModel.navigateTo(Screen.Notifications) },
                                modifier = Modifier.testTag("nav_notifications_bell_btn")
                            ) {
                                BadgedBox(
                                    badge = {
                                        Badge(containerColor = GoldAccent) {
                                            Text("3", color = MaroonPrimaryDark, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                ) {
                                    Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = GoldLight)
                                }
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaroonPrimaryDark
                        )
                    )
                    // Decorative Golden Arch border along TopBar bottom
                    TempleToranArch(
                        color = GoldAccent,
                        modifier = Modifier.background(MaroonPrimaryDark)
                    )
                }
            },
            bottomBar = {
                Column {
                    // Decorative Toran Arch inverted / top of bottom bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(GoldAccent.copy(alpha = 0.2f), GoldAccent, GoldAccent.copy(alpha = 0.2f))
                                )
                            )
                    )
                    NavigationBar(
                        containerColor = MaroonPrimaryDark,
                        contentColor = GoldLight,
                        tonalElevation = 8.dp,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        val items = listOf(
                            BottomNavItem("Home", Screen.Home, Icons.Default.Home),
                            BottomNavItem("Classes", Screen.Classes, Icons.Default.SelfImprovement),
                            BottomNavItem("Events", Screen.Events, Icons.Default.Event),
                            BottomNavItem("Awards", Screen.Achievements, Icons.Default.EmojiEvents),
                            BottomNavItem("Student", Screen.Profile, Icons.Default.Person)
                        )

                        items.forEach { item ->
                            val isSelected = currentScreen == item.screen
                            NavigationBarItem(
                                icon = { Icon(item.icon, contentDescription = item.label) },
                                label = { Text(item.label, fontSize = 11.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                selected = isSelected,
                                onClick = { viewModel.navigateTo(item.screen) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaroonPrimaryDark,
                                    selectedTextColor = GoldLight,
                                    unselectedIconColor = Color.White.copy(alpha = 0.65f),
                                    unselectedTextColor = Color.White.copy(alpha = 0.65f),
                                    indicatorColor = GoldAccent
                                ),
                                modifier = Modifier.testTag("bottom_nav_${item.label.lowercase()}")
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                FloatingWhatsAppButton(
                    phoneNumber = studioInfo.whatsappNumber,
                    defaultMessage = studioInfo.whatsappDefaultMessage
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentScreen) {
                    Screen.Home -> HomeScreen(viewModel = viewModel)
                    Screen.Classes -> ClassesScreen(viewModel = viewModel)
                    Screen.Events -> EventsScreen(viewModel = viewModel)
                    Screen.Achievements -> AchievementsScreen(viewModel = viewModel)
                    Screen.Profile -> StudentPortalScreen(viewModel = viewModel)
                    Screen.MeetGuru -> MeetGuruScreen(viewModel = viewModel)
                    Screen.AboutUs -> AboutUsScreen(viewModel = viewModel)
                    Screen.Gallery -> GalleryScreen(viewModel = viewModel)
                    Screen.Certificates -> CertificatesScreen(viewModel = viewModel)
                    Screen.ContactUs -> ContactUsScreen(viewModel = viewModel)
                    Screen.AdmissionForm -> AdmissionFormScreen(viewModel = viewModel)
                    Screen.AdminPanel -> AdminPanelScreen(viewModel = viewModel)
                    Screen.Notifications -> NotificationsScreen(viewModel = viewModel)
                    Screen.LearningLevels -> LearningLevelsScreen(viewModel = viewModel)
                    Screen.ClassSchedule -> ClassesScreen(viewModel = viewModel)
                }
            }
        }
    }

    // Fullscreen Modals
    if (activeGalleryItem != null) {
        FullscreenGalleryDialog(
            item = activeGalleryItem,
            onDismiss = { viewModel.openGalleryViewer(null) }
        )
    }

    if (activeCertificate != null) {
        CertificateViewerDialog(
            certificate = activeCertificate,
            onDismiss = { viewModel.openCertificateViewer(null) }
        )
    }
}

private data class BottomNavItem(
    val label: String,
    val screen: Screen,
    val icon: ImageVector
)

@Composable
fun DrawerMenuContent(
    studioInfo: StudioInfo,
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamBackground)
    ) {
        // Decorative mandala watermark in drawer background
        MandalaWatermark(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 40.dp),
            color = GoldAccent.copy(alpha = 0.06f)
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Drawer Header with Traditional Logo & Academy Tagline & Gold borders
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(MaroonPrimaryDark, MaroonPrimary)
                        )
                    )
                    .statusBarsPadding()
                    .padding(20.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_nrutyashala),
                            contentDescription = "Nrutyashala Logo",
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .border(2.dp, GoldAccent, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "Nrutyashala",
                                color = GoldLight,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                            Text(
                                text = "The Dance Studio",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    TempleOrnamentalDivider(color = GoldAccent.copy(alpha = 0.6f))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "“${studioInfo.tagline}”",
                        color = GoldAccent,
                        fontSize = 11.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }

            TempleToranArch(color = GoldAccent)

            // Navigation Items List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                DrawerItem("Home", Icons.Default.Home, currentScreen == Screen.Home) { onNavigate(Screen.Home) }
                DrawerItem("About Nrutyashala", Icons.Default.Info, currentScreen == Screen.AboutUs) { onNavigate(Screen.AboutUs) }
                DrawerItem("Meet Your Guru", Icons.Default.Person, currentScreen == Screen.MeetGuru) { onNavigate(Screen.MeetGuru) }
                DrawerItem("Bharatanatyam Classes", Icons.Default.SelfImprovement, currentScreen == Screen.Classes) { onNavigate(Screen.Classes) }
                DrawerItem("Learning Levels & Syllabus", Icons.Default.MenuBook, currentScreen == Screen.LearningLevels) { onNavigate(Screen.LearningLevels) }
                DrawerItem("Student Achievements", Icons.Default.EmojiEvents, currentScreen == Screen.Achievements) { onNavigate(Screen.Achievements) }
                DrawerItem("Media Gallery", Icons.Default.PhotoLibrary, currentScreen == Screen.Gallery) { onNavigate(Screen.Gallery) }
                DrawerItem("Upcoming Events", Icons.Default.Event, currentScreen == Screen.Events) { onNavigate(Screen.Events) }
                DrawerItem("Certificates Portal", Icons.Default.WorkspacePremium, currentScreen == Screen.Certificates) { onNavigate(Screen.Certificates) }
                DrawerItem("Admissions & Enquiry", Icons.Default.Assignment, currentScreen == Screen.AdmissionForm) { onNavigate(Screen.AdmissionForm) }
                DrawerItem("Contact Us", Icons.Default.Phone, currentScreen == Screen.ContactUs) { onNavigate(Screen.ContactUs) }

                HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp), color = CardBorder)

                DrawerItem("Admin Control Panel", Icons.Default.AdminPanelSettings, currentScreen == Screen.AdminPanel) { onNavigate(Screen.AdminPanel) }
            }

            // Drawer Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TempleOrnamentalDivider(color = GoldMuted.copy(alpha = 0.5f))
                Text(
                    text = "Nrutyashala v1.0 • Preserving Classical Heritage",
                    fontSize = 10.sp,
                    color = TextSubtle
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) GoldContainer else Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, GoldAccent.copy(alpha = 0.7f)) else null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isSelected) MaroonPrimary else TextMuted,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) MaroonPrimaryDark else TextCharcoal
            )
            if (isSelected) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "✦", color = GoldAccent, fontSize = 12.sp)
            }
        }
    }
}
