package com.example.repository

import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudioRepository {

    private val _studioInfo = MutableStateFlow(StudioInfo())
    val studioInfo: StateFlow<StudioInfo> = _studioInfo.asStateFlow()

    private val _classes = MutableStateFlow(
        listOf(
            ClassItem(
                id = "c1",
                title = "Classical Bharatanatyam Foundation",
                type = "Offline",
                level = "Beginner",
                schedule = "Tuesday & Thursday",
                timing = "5:00 PM - 6:30 PM",
                duration = "1.5 Hours",
                description = "Comprehensive physical classroom training offering personal posture correction, fundamental Aramandi stances, and introductory Adavus.",
                features = listOf(
                    "Personal attention & posture refinement",
                    "Structured Natya Shastra terminology",
                    "Practical rhythm (Talam) drills",
                    "Akhil Bhartiya Gandharva Mahavidyalaya curriculum",
                    "Performance preparation & stage etiquette"
                ),
                teacher = "Guru Hetal",
                roomLocation = "Studio Hall 1 (Ground Floor)",
                batchCode = "BEG-TH-01"
            ),
            ClassItem(
                id = "c2",
                title = "Junior Adavu Drills & Rhythm",
                type = "Offline",
                level = "Beginner",
                schedule = "Saturday & Sunday",
                timing = "8:30 AM - 10:00 AM",
                duration = "1.5 Hours",
                description = "Weekend foundational batch focusing on Tatta, Natta, and Kuditta Mettu adavus, with basic Samyuta and Asamyuta Hastas.",
                features = listOf(
                    "Weekend batch for school students",
                    "Rhythmic foot tapping & metronome coordination",
                    "Storytelling through animal and deity mudras",
                    "Prarambhik examination readiness"
                ),
                teacher = "Guru Hetal & Senior Faculty",
                roomLocation = "Studio Hall 2 (First Floor)",
                batchCode = "BEG-SS-02"
            ),
            ClassItem(
                id = "c3",
                title = "Intermediate Abhinaya & Jathis",
                type = "Offline",
                level = "Intermediate",
                schedule = "Monday & Wednesday",
                timing = "6:30 PM - 8:00 PM",
                duration = "1.5 Hours",
                description = "Rigorous training focusing on advanced complex Adavus, Jathis rhythm coordination, and facial emotional expressions (Navarasa & Abhinaya).",
                features = listOf(
                    "Complex Korvais & rhythmic footwork",
                    "Expression and emotional portrayal",
                    "Theory of mudras (Asamyuta & Samyuta Hastas)",
                    "Annual studio recitals & youth festivals",
                    "Preparation for Visharad Prathama examination"
                ),
                teacher = "Guru Hetal",
                roomLocation = "Studio Hall 1 (Ground Floor)",
                batchCode = "INT-MW-01"
            ),
            ClassItem(
                id = "c4",
                title = "Alarippu & Jatiswaram Repertoire",
                type = "Offline",
                level = "Intermediate",
                schedule = "Tuesday & Friday",
                timing = "6:30 PM - 8:00 PM",
                duration = "1.5 Hours",
                description = "Mastery of Tisra & Chaturasra Alarippus, and melodic Jatiswarams in classical ragas like Kalyani and Saveri.",
                features = listOf(
                    "Speed variations (1st, 2nd, and 3rd Kalam)",
                    "Live mridangam accompaniment sessions",
                    "Stage presence & ensemble synchrony",
                    "Gandharva Madhyama syllabus"
                ),
                teacher = "Guru Hetal",
                roomLocation = "Ranga Mandiram (Main Hall)",
                batchCode = "INT-TF-02"
            ),
            ClassItem(
                id = "c5",
                title = "Global Online Interactive Studio",
                type = "Online",
                level = "All Levels",
                schedule = "Saturday & Sunday",
                timing = "9:00 AM - 10:30 AM IST",
                duration = "1.5 Hours",
                description = "High-definition live interactive sessions for out-of-town and international students seeking authentic traditional guru-shishya learning from home.",
                features = listOf(
                    "Live two-way video instruction with real-time feedback",
                    "Recorded practice reference modules for home practice",
                    "Flexible schedule for students across time zones",
                    "One-on-one monthly technique review",
                    "Online theory workshops & examinations eligibility"
                ),
                teacher = "Guru Hetal",
                roomLocation = "Virtual Studio (Live Google Meet)",
                batchCode = "ONL-GL-01"
            ),
            ClassItem(
                id = "c6",
                title = "Advanced Margam & Arangetram Intensive",
                type = "Offline",
                level = "Advanced",
                schedule = "Friday & Saturday",
                timing = "6:00 PM - 8:00 PM",
                duration = "2.0 Hours",
                description = "Mastery of the complete traditional Bharatanatyam Margam (Alarippu, Jatiswaram, Shabdam, Varnam, Padam, Tillana) tailored for solo stage debuts.",
                features = listOf(
                    "Mastery of intricate Varnam and Tillana",
                    "Stage presence & live orchestra coordination",
                    "Individual solo choreography mastery",
                    "Complete Arangetram preparation",
                    "Gandharva Mahavidyalaya Visharad certification guidance"
                ),
                teacher = "Guru Hetal",
                roomLocation = "Nataraja Auditorium & Stage",
                batchCode = "ADV-FS-01"
            ),
            ClassItem(
                id = "c7",
                title = "Theory, Natyashastra & Talam Practicum",
                type = "Offline",
                level = "All Levels",
                schedule = "Sunday",
                timing = "11:00 AM - 12:30 PM",
                duration = "1.5 Hours",
                description = "Dedicated theory masterclass exploring Abhinaya Darpana slokas, Talam beat patterns, Raga recognition, and dance history.",
                features = listOf(
                    "Sloka recitation with correct Sanskrit diction",
                    "Hands-on Nattuvangam cymbals training",
                    "Written exam preparation & mock viva",
                    "Open to all registered Nrutyashala disciples"
                ),
                teacher = "Guru Hetal & Guest Musicologists",
                roomLocation = "Sanskrit Library & Seminar Room",
                batchCode = "TH-SUN-01"
            )
        )
    )
    val classes: StateFlow<List<ClassItem>> = _classes.asStateFlow()

    private val _learningLevels = MutableStateFlow(
        listOf(
            LearningLevel(
                id = "lvl1",
                levelName = "Beginner (Prarambhik)",
                subtitle = "Building Rooted Discipline & Sacred Posture",
                description = "Introduces students to the sacred foundations of Bharatanatyam: Namaskaram, Araimandi, basic stances, and fundamental rhythmic cycles.",
                syllabusTopics = listOf(
                    "Namaskaram (Offering prayer to Mother Earth, Nataraja, and Guru)",
                    "Stances: Araimandi (Half-sit), Muzhumandi (Full-sit), Samapadam",
                    "Tatta Adavu & Natta Adavu series",
                    "Asamyuta Hastas (28 Single-hand gestures according to Abhinaya Darpana)",
                    "Basic Talam counts (Chaturasra Jati & Tisra Jati basics)"
                ),
                keyFocus = listOf("Body balance", "Foot clarity", "Spinal alignment", "Discipline"),
                durationApprox = "1 - 2 Years"
            ),
            LearningLevel(
                id = "lvl2",
                levelName = "Intermediate (Praveshika & Madhyama)",
                subtitle = "Rhythm Complexity & Emergence of Abhinaya",
                description = "Students expand their vocabulary into swift combinations, dynamic jumps, hand-eye synchronization, and introductory expressional storytelling.",
                syllabusTopics = listOf(
                    "Kuditta Mettu, Pakka, Sarikkal, and Kudittu Tatta Adavus",
                    "Samyuta Hastas (24 Combined-hand gestures) and Shiro/Drishti Bhedas (Head and Eye movements)",
                    "Alarippu (Tisram & Misram) mastery",
                    "Jatiswaram in classical Ragas",
                    "Introduction to Navarasa (Nine aesthetic sentiments)",
                    "Theory: Natyashastra background & history of Bharatanatyam"
                ),
                keyFocus = listOf("Stamina", "Speed variation (Kalas)", "Graceful mudras", "Musicality"),
                durationApprox = "2 - 3 Years"
            ),
            LearningLevel(
                id = "lvl3",
                levelName = "Advanced (Visharad & Arangetram)",
                subtitle = "Solo Mastery, Deep Emotion & Stage Elevation",
                description = "Intensive professional training covering the monumental centerpiece of Bharatanatyam (Varnam), devotional Padams, dynamic Tillana, and sacred solo debut preparation.",
                syllabusTopics = listOf(
                    "Mastery of Varnam with complex Jathis and poetic Sanchari Bhavas",
                    "Expressive solo Padams, Javalis, and Keerthanams",
                    "Vibrant, energetic Tillana and Mangalam",
                    "Live orchestra collaboration (Mridangam, Nattuvangam, Vocal, Violin)",
                    "Gandharva Mahavidyalaya Visharad Examination & Diploma thesis"
                ),
                keyFocus = listOf("Bhav (Expression)", "Stage command", "Artistic autonomy", "Arangetram debut"),
                durationApprox = "3+ Years"
            )
        )
    )
    val learningLevels: StateFlow<List<LearningLevel>> = _learningLevels.asStateFlow()

    private val _achievements = MutableStateFlow(
        listOf(
            StudentAchievement(
                id = "a1",
                studentName = "Nrutyashala Ensemble",
                title = "State Classical Dance Championship",
                category = "Competition",
                eventOrExam = "All Gujarat Classical Youth Kalautsavam",
                year = "2025",
                details = "Awarded 1st Place Gold Medal for pristine synchronization in traditional Tillana performance choreography.",
                medalOrAward = "Gold Trophy & 1st Prize",
                imageResName = "img_performance_event"
            ),
            StudentAchievement(
                id = "a2",
                studentName = "Rhea & Ananya",
                title = "Distinction in Visharad Pratham",
                category = "Examination",
                eventOrExam = "Akhil Bhartiya Gandharva Mahavidyalaya Mandal",
                year = "2025",
                details = "Secured Outstanding Distinction in both theory and practical Viva for 5th year Bharatanatyam board examination.",
                medalOrAward = "Merit Certificate of Distinction",
                imageResName = "img_dance_class"
            ),
            StudentAchievement(
                id = "a3",
                studentName = "Nrutyashala Senior Troupe",
                title = "Prestigious Cultural Showcase",
                category = "Performance",
                eventOrExam = "Vibrant Gujarat Cultural Conclave",
                year = "2024",
                details = "Selected to present a thematic classical piece on Indian heritage before international dignitaries under Guru Hetal's direction.",
                medalOrAward = "Certificate of Honor",
                imageResName = "img_hero_banner"
            ),
            StudentAchievement(
                id = "a4",
                studentName = "Pooja Patel",
                title = "Solo Arangetram Graduation Debut",
                category = "Arangetram",
                eventOrExam = "Nrutyashala Rangapravesham Stage",
                year = "2024",
                details = "Successfully completed 3-hour uninterrupted solo classical repertoire with full live orchestra accompaniment.",
                medalOrAward = "Nrutyamani Title",
                imageResName = "img_performance_event"
            )
        )
    )
    val achievements: StateFlow<List<StudentAchievement>> = _achievements.asStateFlow()

    private val _events = MutableStateFlow(
        listOf(
            AcademyEvent(
                id = "e1",
                title = "Natyanjali Annual Academy Showcase 2026",
                date = "November 14, 2026",
                time = "6:00 PM - 9:00 PM",
                location = "Town Hall Auditorium, Cultural Circle",
                description = "An enchanting evening where junior, intermediate, and senior disciples of Nrutyashala present traditional pieces dedicated to Lord Nataraja.",
                isRegistrationOpen = true,
                imageResName = "img_performance_event"
            ),
            AcademyEvent(
                id = "e2",
                title = "Navarasa & Abhinaya Masterclass Workshop",
                date = "October 24, 2026",
                time = "10:00 AM - 1:00 PM",
                location = "Nrutyashala Studio Main Hall / Online Zoom",
                description = "Specialized intensive workshop breaking down the nine emotional rasas and their subtle facial muscle control, open to all learning levels.",
                isRegistrationOpen = true,
                imageResName = "img_dance_class"
            ),
            AcademyEvent(
                id = "e3",
                title = "Gandharva Mahavidyalaya Winter Board Practical Exam",
                date = "December 5, 2026",
                time = "9:00 AM onwards",
                location = "Nrutyashala Examination Center",
                description = "Official practical and oral viva examinations conducted by certified external examiners for Prarambhik, Praveshika, and Visharad candidates.",
                isRegistrationOpen = false,
                imageResName = "img_guru_hetal"
            )
        )
    )
    val events: StateFlow<List<AcademyEvent>> = _events.asStateFlow()

    private val _gallery = MutableStateFlow(
        listOf(
            GalleryItem(
                id = "g1",
                title = "Nataraja Vandana Invocation",
                category = "Performances",
                description = "Senior students performing the opening invocation prayer on stage.",
                imageResName = "img_hero_banner"
            ),
            GalleryItem(
                id = "g2",
                title = "Guru Hetal Demonstrating Mudras",
                category = "Guru",
                description = "Founder & Guru Hetal guiding students through intricate Abhinaya nuances.",
                imageResName = "img_guru_hetal"
            ),
            GalleryItem(
                id = "g3",
                title = "Studio Adavu Drills",
                category = "Classes",
                description = "Students perfecting the Araimandi posture in the studio mirror hall.",
                imageResName = "img_dance_class"
            ),
            GalleryItem(
                id = "g4",
                title = "State Festival Ensemble",
                category = "Events",
                description = "Vibrant stage presentation by Nrutyashala at the regional festival.",
                imageResName = "img_performance_event"
            ),
            GalleryItem(
                id = "g5",
                title = "Traditional Arangetram Stage Solo",
                category = "Arangetram",
                description = "Soloist presenting the majestic Varnam in silk temple attire.",
                imageResName = "img_hero_banner"
            ),
            GalleryItem(
                id = "g6",
                title = "Talam & Rhythm Workshop",
                category = "Workshops",
                description = "Mastering rhythmic cycles with talam sticks and hand beats.",
                imageResName = "img_dance_class"
            )
        )
    )
    val gallery: StateFlow<List<GalleryItem>> = _gallery.asStateFlow()

    private val _notifications = MutableStateFlow(
        listOf(
            AppNotification(
                id = "n1",
                title = "Winter Batch Admissions Open",
                message = "Admissions for offline foundation and global online classes are now open. Limited seats per batch.",
                timestamp = "2 hours ago",
                category = "General"
            ),
            AppNotification(
                id = "n2",
                title = "Natyanjali Showcase Rehearsals",
                message = "Costume measurements and stage blocking schedule published for intermediate & senior batches.",
                timestamp = "1 day ago",
                category = "Event"
            ),
            AppNotification(
                id = "n3",
                title = "Gandharva Exam Hall Tickets Issued",
                message = "Students appearing for Praveshika Pratham may collect their roll numbers from Guru Hetal.",
                timestamp = "3 days ago",
                category = "Exam"
            )
        )
    )
    val notifications: StateFlow<List<AppNotification>> = _notifications.asStateFlow()

    private val _currentStudent = MutableStateFlow<StudentUser?>(
        StudentUser(
            id = "s_101",
            name = "Aaradhya Sharma",
            studentId = "NRUT-2024-042",
            email = "aaradhya.sharma@example.com",
            phone = "+91 98765 12345",
            currentLevel = "Intermediate (Praveshika Purna)",
            batch = "Batch B - Mon/Wed 6:30 PM",
            mode = "Offline",
            joinDate = "August 2023",
            attendedClasses = 23,
            totalClasses = 25,
            adavusProgressPct = 88,
            theoryProgressPct = 94,
            practicalProgressPct = 90,
            overallProgressPct = 92,
            examReadiness = "Ready for Madhyama Prathama"
        )
    )
    val currentStudent: StateFlow<StudentUser?> = _currentStudent.asStateFlow()

    private val _studentCertificates = MutableStateFlow(
        listOf(
            Certificate(
                id = "cert_1",
                certificateId = "CERT-NRUT-2024-089",
                title = "Praveshika Pratham Certificate of Merit",
                studentName = "Aaradhya Sharma",
                courseOrExam = "Bharatanatyam Classical Foundation",
                issuedDate = "April 15, 2024",
                grade = "First Class with Distinction",
                issuingBody = "Akhil Bhartiya Gandharva Mahavidyalaya & Nrutyashala"
            ),
            Certificate(
                id = "cert_2",
                certificateId = "CERT-NRUT-2025-014",
                title = "Natyanjali Annual Festival Solo Performer",
                studentName = "Aaradhya Sharma",
                courseOrExam = "Alarippu & Jathiswaram Stage Performance",
                issuedDate = "November 18, 2025",
                grade = "Gold Performance Honor",
                issuingBody = "Nrutyashala – The Dance Studio"
            )
        )
    )
    val studentCertificates: StateFlow<List<Certificate>> = _studentCertificates.asStateFlow()

    private val _enquiries = MutableStateFlow(
        listOf(
            AdmissionEnquiry(
                id = "enq_1",
                studentName = "Meera Joshi",
                parentName = "Sunita Joshi",
                age = "8",
                phone = "+91 98250 11223",
                email = "meera.family@example.com",
                city = "Ahmedabad",
                preferredClass = "Classical Bharatanatyam Foundation",
                mode = "Offline",
                experienceLevel = "Beginner",
                message = "Eager to learn authentic traditional dance under Guru Hetal.",
                dateSubmitted = "Oct 4, 2026",
                status = "Contacted"
            ),
            AdmissionEnquiry(
                id = "enq_2",
                studentName = "Divya Nair",
                parentName = "Self",
                age = "24",
                phone = "+91 99090 44556",
                email = "divya.nair@example.com",
                city = "London, UK",
                preferredClass = "Global Online Interactive Studio",
                mode = "Online",
                experienceLevel = "Intermediate",
                message = "Looking for weekend online guru guidance to resume dance practice.",
                dateSubmitted = "Oct 5, 2026",
                status = "New"
            )
        )
    )
    val enquiries: StateFlow<List<AdmissionEnquiry>> = _enquiries.asStateFlow()

    // Actions
    fun submitEnquiry(enquiry: AdmissionEnquiry) {
        val updated = listOf(enquiry) + _enquiries.value
        _enquiries.value = updated
    }

    fun updateStudioContact(phone: String, whatsapp: String, email: String, address: String) {
        _studioInfo.value = _studioInfo.value.copy(
            phone = phone,
            whatsappNumber = whatsapp,
            email = email,
            address = address
        )
    }

    fun addClass(newClass: ClassItem) {
        _classes.value = _classes.value + newClass
    }

    fun deleteClass(classId: String) {
        _classes.value = _classes.value.filter { it.id != classId }
    }

    fun addAchievement(item: StudentAchievement) {
        _achievements.value = listOf(item) + _achievements.value
    }

    fun deleteAchievement(id: String) {
        _achievements.value = _achievements.value.filter { it.id != id }
    }

    fun addEvent(event: AcademyEvent) {
        _events.value = listOf(event) + _events.value
    }

    fun deleteEvent(id: String) {
        _events.value = _events.value.filter { it.id != id }
    }

    fun addGalleryItem(item: GalleryItem) {
        _gallery.value = listOf(item) + _gallery.value
    }

    fun deleteGalleryItem(id: String) {
        _gallery.value = _gallery.value.filter { it.id != id }
    }

    fun markAttendance(attendedDelta: Int, totalDelta: Int) {
        _currentStudent.value?.let { current ->
            val newAttended = (current.attendedClasses + attendedDelta).coerceAtLeast(0)
            val newTotal = (current.totalClasses + totalDelta).coerceAtLeast(1)
            val newPct = ((newAttended.toFloat() / newTotal.toFloat()) * 100).toInt()
            _currentStudent.value = current.copy(
                attendedClasses = newAttended,
                totalClasses = newTotal,
                overallProgressPct = newPct
            )
        }
    }

    fun updateStudentProgress(adavus: Int, theory: Int, practical: Int) {
        _currentStudent.value?.let { current ->
            val avg = ((adavus + theory + practical) / 3)
            _currentStudent.value = current.copy(
                adavusProgressPct = adavus,
                theoryProgressPct = theory,
                practicalProgressPct = practical,
                overallProgressPct = avg
            )
        }
    }

    fun loginStudent(name: String, studentId: String) {
        _currentStudent.value = StudentUser(
            id = "s_${System.currentTimeMillis()}",
            name = name.ifBlank { "Aaradhya Sharma" },
            studentId = studentId.ifBlank { "NRUT-2024-042" },
            email = "student@nrutyashala.com",
            phone = "+91 98765 00000",
            currentLevel = "Intermediate (Praveshika Purna)",
            batch = "Weekend Interactive Batch",
            mode = "Offline",
            joinDate = "October 2024",
            attendedClasses = 24,
            totalClasses = 26,
            adavusProgressPct = 90,
            theoryProgressPct = 85,
            practicalProgressPct = 92,
            overallProgressPct = 89,
            examReadiness = "Active Preparation"
        )
    }

    fun logoutStudent() {
        _currentStudent.value = null
    }

    fun sendNotification(title: String, message: String, category: String) {
        val newNotif = AppNotification(
            id = "n_${System.currentTimeMillis()}",
            title = title,
            message = message,
            timestamp = "Just now",
            category = category
        )
        _notifications.value = listOf(newNotif) + _notifications.value
    }

    fun updateEnquiryStatus(id: String, newStatus: String) {
        _enquiries.value = _enquiries.value.map {
            if (it.id == id) it.copy(status = newStatus) else it
        }
    }
}
