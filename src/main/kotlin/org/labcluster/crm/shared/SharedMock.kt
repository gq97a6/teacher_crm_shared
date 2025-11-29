package org.labcluster.crm.shared

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.labcluster.crm.shared.model.*

@Open
class SharedMock {

    val students = listOf(
        Student("Adam", "Nowak"),
        Student("Anna", "Kowalska"),
        Student("Piotr", "Wiśniewski"),
        Student("Maria", "Wójcik"),
        Student("Krzysztof", "Kowalczyk"),
        Student("Agnieszka", "Kamińska"),
        Student("Tomasz", "Lewandowski"),
        Student("Barbara", "Zielińska"),
        Student("Jan", "Szymański"),
        Student("Katarzyna", "Woźniak"),
        Student("Michał", "Dąbrowski"),
        Student("Ewa", "Kozłowska"),
        Student("Marcin", "Jankowski"),
        Student("Małgorzata", "Mazur"),
        Student("Andrzej", "Wojciechowski"),
        Student("Teresa", "Kwiatkowska"),
        Student("Paweł", "Krawczyk"),
        Student("Magdalena", "Kaczmarek"),
        Student("Stanisław", "Piotrowski"),
        Student("Krystyna", "Grabowska"),
        Student("Grzegorz", "Pawłowski"),
        Student("Zofia", "Michalska"),
        Student("Marek", "Król"),
        Student("Elżbieta", "Zając"),
        Student("Łukasz", "Wieczorek"),
        Student("Joanna", "Jabłońska"),
        Student("Józef", "Wróbel"),
        Student("Aleksandra", "Nowakowska"),
        Student("Kamil", "Majewski"),
        Student("Monika", "Olszewska"),
        Student("Tadeusz", "Stępień"),
        Student("Danuta", "Malinowska"),
        Student("Ryszard", "Jaworski"),
        Student("Natalia", "Adamczyk"),
        Student("Dariusz", "Dudek"),
        Student("Karolina", "Nowicka"),
        Student("Henryk", "Pawlak"),
        Student("Marta", "Górska"),
        Student("Mateusz", "Sikora"),
        Student("Beata", "Witkowska"),
        Student("Rafał", "Walczak")
    )

    val teachers = listOf(
        Teacher("Anna", "Nowak"),
        Teacher("Jan", "Kowalski"),
        Teacher("Maria", "Wiśniewska"),
        Teacher("Piotr", "Wójcik")
    )

    val topics = listOf(
        Topic("Supervised vs. Unsupervised Learning"),
        Topic("Linear & Logistic Regression"),
        Topic("Decision Trees & Random Forests"),
        Topic("Model Evaluation Metrics"),
        Topic("Feature Engineering"),
        Topic("Version Control with Git"),
        Topic("Continuous Integration & Delivery (CI/CD)"),
        Topic("Infrastructure as Code (IaC)"),
        Topic("Containerization with Docker"),
        Topic("Monitoring & Logging"),
        Topic("Network Security Principles"),
        Topic("Cryptography & Encryption"),
        Topic("Malware & Threat Vectors"),
        Topic("Access Control & Identity Management"),
        Topic("Web Application Security (OWASP Top 10)"),
        Topic("IaaS, PaaS, SaaS Models"),
        Topic("Virtualization & Containers"),
        Topic("Cloud Storage Solutions"),
        Topic("Serverless Architecture"),
        Topic("Cloud Security Basics"),
        Topic("Big O Notation"),
        Topic("Arrays & Linked Lists"),
        Topic("Stacks & Queues"),
        Topic("Trees & Graphs"),
        Topic("Sorting & Searching Algorithms"),
        Topic("SQL Queries"),
        Topic("Relational Database Design"),
        Topic("Normalization"),
        Topic("Transactions & Concurrency"),
        Topic("Indexes & Performance Tuning"),
        Topic("HTML5"),
        Topic("CSS3 & Selectors"),
        Topic("JavaScript Basics"),
        Topic("DOM Manipulation"),
        Topic("HTTP & REST APIs"),
        Topic("Variables & Data Types"),
        Topic("Control Flow (If/Else, Loops)"),
        Topic("Functions & Methods"),
        Topic("Basic Data Structures"),
        Topic("Object-Oriented Programming Concepts")
    )

    val courses = listOf(
        Course(
            name = "Introduction to Programming",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Web Development Fundamentals",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Database Management",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Data Structures & Algorithms",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Cloud Computing Essentials",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Cybersecurity Basics",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "DevOps Fundamentals",
            topics = topics.shuffled().take(15).toMutableList()
        ),
        Course(
            name = "Introduction to Machine Learning",
            topics = topics.shuffled().take(15).toMutableList()
        )
    )

    private val timeZone = TimeZone.currentSystemDefault()
    private fun createEpochStart(year: Int, month: Int, day: Int, timeEpoch: Long) = LocalDateTime(
        year,
        month,
        day,
        hour = 0,
        minute = 0
    ).toInstant(timeZone).epochSeconds + timeEpoch

    val groups = buildList {
        teachers.forEach { teacher ->
            repeat(5) { dayIndex -> //Five days a week
                repeat(2) { groupIndex -> //Create two groups per day
                    val isMorning = groupIndex == 0
                    val students = students.shuffled().take(6).toMutableList()
                    val minute = listOf(0, 15, 30, 45).random()
                    val hour = if (isMorning) listOf(8, 10, 12).random()
                    else listOf(14, 16, 18).random()

                    Group(
                        teacher = teacher,
                        students = students,
                        dayIndex = dayIndex,
                        timeEpoch = hour * 3600L + minute * 60L
                    ).let { add(it) }
                }
            }
        }
    }

    val groupLessons: Map<String, List<Lesson>> = buildMap {
        //For every week of the year
        repeat(3) { yearOffsetIndex ->
            repeat(12) { monthIndex ->
                repeat(4) { weekIndex ->
                    groups.forEach { group ->
                        val epochStart = createEpochStart(
                            year = 2024 + yearOffsetIndex,
                            month = monthIndex + 1,
                            day = weekIndex * 7 + group.dayIndex + 1,
                            timeEpoch = group.timeEpoch
                        )

                        val lesson = Lesson(
                            epochStart = epochStart,
                            topic = topics.random(),
                            course = courses.random(),
                            teacher1 = group.teacher,
                            teacher2 = null,
                            students = group.students
                        )

                        //Update the list
                        val currentList = get(group.uuid) ?: listOf()
                        val newList = currentList + lesson
                        set(group.uuid, newList)
                    }
                }
            }
        }
    }

    val lessons: List<Lesson> = groupLessons.values.flatten()
}