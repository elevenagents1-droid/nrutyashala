package com.example.model

data class ClassItem(
    val id: String,
    val title: String,
    val type: String, // "Offline" or "Online"
    val level: String, // "Beginner", "Intermediate", "Advanced", "All Levels"
    val schedule: String,
    val timing: String,
    val duration: String,
    val description: String,
    val features: List<String>,
    val teacher: String = "Guru Hetal",
    val roomLocation: String = "Main Natya Hall",
    val batchCode: String = "BATCH-A"
)

data class LearningLevel(
    val id: String,
    val levelName: String,
    val subtitle: String,
    val description: String,
    val syllabusTopics: List<String>,
    val keyFocus: List<String>,
    val durationApprox: String
)

data class StudentAchievement(
    val id: String,
    val studentName: String,
    val title: String,
    val category: String, // "Competition", "Examination", "Performance", "Arangetram"
    val eventOrExam: String,
    val year: String,
    val details: String,
    val medalOrAward: String,
    val imageResName: String = "img_performance_event"
)

data class AcademyEvent(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val description: String,
    val isRegistrationOpen: Boolean = true,
    val imageResName: String = "img_performance_event"
)

data class GalleryItem(
    val id: String,
    val title: String,
    val category: String, // "Performances", "Classes", "Events", "Guru", "Arangetram", "Workshops"
    val description: String,
    val imageResName: String, // maps to drawable
    val isVideo: Boolean = false,
    val videoUrl: String = ""
)

data class AppNotification(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: String,
    val category: String, // "Class", "Event", "Exam", "General"
    val isRead: Boolean = false
)

data class StudentUser(
    val id: String,
    val name: String,
    val studentId: String,
    val email: String,
    val phone: String,
    val currentLevel: String,
    val batch: String,
    val mode: String, // "Offline" or "Online"
    val joinDate: String,
    val attendedClasses: Int,
    val totalClasses: Int,
    val adavusProgressPct: Int,
    val theoryProgressPct: Int,
    val practicalProgressPct: Int,
    val overallProgressPct: Int,
    val examReadiness: String
)

data class Certificate(
    val id: String,
    val certificateId: String,
    val title: String,
    val studentName: String,
    val courseOrExam: String,
    val issuedDate: String,
    val grade: String,
    val issuingBody: String = "Akhil Bhartiya Gandharva Mahavidyalaya / Nrutyashala",
    val verified: Boolean = true
)

data class AdmissionEnquiry(
    val id: String,
    val studentName: String,
    val parentName: String,
    val age: String,
    val phone: String,
    val email: String,
    val city: String,
    val preferredClass: String,
    val mode: String, // "Online" or "Offline"
    val experienceLevel: String, // "Beginner", "Intermediate", "Advanced"
    val message: String,
    val dateSubmitted: String,
    val status: String = "New" // "New", "Contacted", "Enrolled", "Closed"
)

data class StudioInfo(
    val studioName: String = "Nrutyashala – The Dance Studio",
    val tagline: String = "Preserving Tradition. Inspiring the Next Generation.",
    val founderName: String = "Guru Hetal",
    val founderQualifications: List<String> = listOf(
        "Bachelor of Arts in Bharatanatyam",
        "Visharad in Bharatanatyam",
        "Registered Teacher with Akhil Bhartiya Gandharva Mahavidyalaya",
        "Performance Experience at Prestigious Events including Vibrant Gujarat Summit",
        "Arangetram Performer & Trainer with Years of Pedagogical Excellence"
    ),
    val phone: String = "+91 98765 43210",
    val whatsappNumber: String = "+91 98765 43210",
    val email: String = "nrutyashala.dance@gmail.com",
    val address: String = "Nrutyashala Studio, Cultural Hub, Gujarat, India",
    val websiteUrl: String = "https://sites.google.com/view/nrutyashalathedancestudio",
    val whatsappDefaultMessage: String = "Namaste! I am interested in joining Nrutyashala The Dance Studio. Please share information about classes and admission."
)
