package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.*
import com.example.repository.StudioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class Screen(val title: String) {
    object Home : Screen("Home")
    object Classes : Screen("Classes")
    object Events : Screen("Events")
    object Achievements : Screen("Achievements")
    object Profile : Screen("Student Portal")
    object MeetGuru : Screen("Meet Your Guru")
    object AboutUs : Screen("About Nrutyashala")
    object Gallery : Screen("Media Gallery")
    object Certificates : Screen("Certificates")
    object ContactUs : Screen("Contact Us")
    object AdmissionForm : Screen("Enroll / Admission")
    object AdminPanel : Screen("Academy Administration")
    object Notifications : Screen("Notifications")
    object LearningLevels : Screen("Learning Levels")
    object ClassSchedule : Screen("Class Schedule")
}

class MainViewModel(
    val repository: StudioRepository = StudioRepository()
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _navigationStack = mutableListOf<Screen>(Screen.Home)

    // Admin mode toggle & auth
    private val _isAdminLoggedIn = MutableStateFlow(false)
    val isAdminLoggedIn: StateFlow<Boolean> = _isAdminLoggedIn.asStateFlow()

    // UI Feedback (e.g. snackbar message)
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    // Selected Gallery category filter
    private val _selectedGalleryCategory = MutableStateFlow("All")
    val selectedGalleryCategory: StateFlow<String> = _selectedGalleryCategory.asStateFlow()

    // Fullscreen media viewer
    private val _activeGalleryItem = MutableStateFlow<GalleryItem?>(null)
    val activeGalleryItem: StateFlow<GalleryItem?> = _activeGalleryItem.asStateFlow()

    // Active Certificate Modal
    private val _activeCertificate = MutableStateFlow<Certificate?>(null)
    val activeCertificate: StateFlow<Certificate?> = _activeCertificate.asStateFlow()

    // Exposed Flows from Repo
    val studioInfo = repository.studioInfo
    val classes = repository.classes
    val learningLevels = repository.learningLevels
    val achievements = repository.achievements
    val events = repository.events
    val gallery = repository.gallery
    val notifications = repository.notifications
    val currentStudent = repository.currentStudent
    val studentCertificates = repository.studentCertificates
    val enquiries = repository.enquiries

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            _navigationStack.add(screen)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        return if (_navigationStack.size > 1) {
            _navigationStack.removeAt(_navigationStack.lastIndex)
            _currentScreen.value = _navigationStack.last()
            true
        } else {
            false
        }
    }

    fun showMessage(msg: String) {
        viewModelScope.launch {
            _snackbarMessage.value = msg
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    fun setGalleryCategory(cat: String) {
        _selectedGalleryCategory.value = cat
    }

    fun openGalleryViewer(item: GalleryItem?) {
        _activeGalleryItem.value = item
    }

    fun openCertificateViewer(cert: Certificate?) {
        _activeCertificate.value = cert
    }

    fun submitEnquiry(enquiry: AdmissionEnquiry) {
        repository.submitEnquiry(enquiry)
        showMessage("Thank you for contacting Nrutyashala. Our team will contact you shortly.")
    }

    fun updateStudioContact(phone: String, whatsapp: String, email: String, address: String) {
        repository.updateStudioContact(phone, whatsapp, email, address)
        showMessage("Studio contact details updated successfully.")
    }

    fun addClass(newClass: ClassItem) {
        repository.addClass(newClass)
        showMessage("New class batch created.")
    }

    fun deleteClass(id: String) {
        repository.deleteClass(id)
        showMessage("Class deleted.")
    }

    fun addAchievement(ach: StudentAchievement) {
        repository.addAchievement(ach)
        showMessage("Achievement record added.")
    }

    fun deleteAchievement(id: String) {
        repository.deleteAchievement(id)
        showMessage("Achievement removed.")
    }

    fun addEvent(event: AcademyEvent) {
        repository.addEvent(event)
        showMessage("Academy event created.")
    }

    fun deleteEvent(id: String) {
        repository.deleteEvent(id)
        showMessage("Event removed.")
    }

    fun addGalleryItem(item: GalleryItem) {
        repository.addGalleryItem(item)
        showMessage("Media added to academy gallery.")
    }

    fun deleteGalleryItem(id: String) {
        repository.deleteGalleryItem(id)
        showMessage("Gallery media removed.")
    }

    fun markAttendance(attended: Int, total: Int) {
        repository.markAttendance(attended, total)
        showMessage("Attendance updated for student.")
    }

    fun updateStudentProgress(adavus: Int, theory: Int, practical: Int) {
        repository.updateStudentProgress(adavus, theory, practical)
        showMessage("Student learning progress updated.")
    }

    fun sendNotification(title: String, message: String, category: String) {
        repository.sendNotification(title, message, category)
        showMessage("Notification sent to students.")
    }

    fun updateEnquiryStatus(id: String, status: String) {
        repository.updateEnquiryStatus(id, status)
        showMessage("Enquiry marked as $status.")
    }

    fun loginStudent(name: String, studentId: String) {
        repository.loginStudent(name, studentId)
        showMessage("Welcome back, ${name.ifBlank { "Student" }}!")
    }

    fun logoutStudent() {
        repository.logoutStudent()
        showMessage("Logged out successfully.")
    }

    fun loginAdmin(pin: String): Boolean {
        return if (pin == "1234" || pin == "nrutya") {
            _isAdminLoggedIn.value = true
            showMessage("Admin mode authorized.")
            true
        } else {
            showMessage("Invalid Admin PIN (Try 1234).")
            false
        }
    }

    fun logoutAdmin() {
        _isAdminLoggedIn.value = false
        showMessage("Admin mode exited.")
    }
}
