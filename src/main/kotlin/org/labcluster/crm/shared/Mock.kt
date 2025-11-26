package org.labcluster.crm.shared

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.shared.model.*
import kotlin.time.Clock

object Mock {
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
        Teacher("Piotr", "Wójcik"),
        Teacher("Katarzyna", "Kowalczyk"),
        Teacher("Krzysztof", "Kamiński"),
        Teacher("Małgorzata", "Lewandowska"),
        Teacher("Tomasz", "Zieliński"),
        Teacher("Agnieszka", "Szymańska"),
        Teacher("Paweł", "Woźniak"),
        Teacher("Krystyna", "Dąbrowska"),
        Teacher("Michał", "Kozłowski"),
        Teacher("Barbara", "Jankowska"),
        Teacher("Andrzej", "Mazur"),
        Teacher("Ewa", "Wojciechowska"),
        Teacher("Grzegorz", "Kwiatkowski"),
        Teacher("Elżbieta", "Krawczyk"),
        Teacher("Marcin", "Kaczmarek"),
        Teacher("Zofia", "Piotrowska"),
        Teacher("Adam", "Grabowski"),
        Teacher("Teresa", "Pawłowska"),
        Teacher("Stanisław", "Michalski"),
        Teacher("Magdalena", "Król"),
        Teacher("Łukasz", "Zając"),
        Teacher("Joanna", "Wieczorek"),
        Teacher("Józef", "Jabłoński"),
        Teacher("Monika", "Wróbel"),
        Teacher("Jakub", "Dudek"),
        Teacher("Danuta", "Majewska"),
        Teacher("Mateusz", "Olszewski"),
        Teacher("Jadwiga", "Stępień"),
        Teacher("Wojciech", "Malinowski"),
        Teacher("Halina", "Jaworska"),
        Teacher("Mariusz", "Adamczyk"),
        Teacher("Irena", "Górska"),
        Teacher("Dariusz", "Nowicki"),
        Teacher("Beata", "Pawlak"),
        Teacher("Zbigniew", "Sikora"),
        Teacher("Alicja", "Witkowska"),
        Teacher("Jerzy", "Walczak"),
        Teacher("Dorota", "Baran"),
        Teacher("Maciej", "Rutkowski"),
        Teacher("Jolanta", "Michalak"),
        Teacher("Robert", "Szewczyk"),
        Teacher("Iwona", "Ostrowska"),
        Teacher("Kamil", "Tomaszewski"),
        Teacher("Grażyna", "Pietrzak"),
        Teacher("Rafał", "Zalewski"),
        Teacher("Paulina", "Wróblewska"),
        Teacher("Dawid", "Jasiński"),
        Teacher("Renata", "Marciniak"),
        Teacher("Szymon", "Sadowski"),
        Teacher("Marta", "Bąk"),
        Teacher("Kacper", "Zawadzki"),
        Teacher("Agata", "Jakubowska"),
        Teacher("Ryszard", "Wilk"),
        Teacher("Natalia", "Chmielewska"),
        Teacher("Artur", "Borkowski"),
        Teacher("Justyna", "Sokołowska"),
        Teacher("Janusz", "Szczepański"),
        Teacher("Karolina", "Sawicka"),
        Teacher("Henryk", "Kucharski"),
        Teacher("Aleksandra", "Lis"),
        Teacher("Mirosław", "Maciejewski"),
        Teacher("Helena", "Kubiak"),
        Teacher("Kazimierz", "Kalinowski"),
        Teacher("Patrycja", "Mazurek"),
        Teacher("Jacek", "Wysocki"),
        Teacher("Urszula", "Kołodziej"),
        Teacher("Marek", "Kaźmierczak"),
        Teacher("Wiktoria", "Czarnecka"),
        Teacher("Przemysław", "Sobczak"),
        Teacher("Sylwia", "Konieczna"),
        Teacher("Karol", "Krupa"),
        Teacher("Oliwia", "Głowacka"),
        Teacher("Franciszek", "Zakrzewski"),
        Teacher("Julia", "Wasilewska"),
        Teacher("Damian", "Laskowski"),
        Teacher("Dominika", "Krajewska"),
        Teacher("Arkadiusz", "Gajewski"),
        Teacher("Aneta", "Mróz"),
        Teacher("Sebastian", "Czerwiński"),
        Teacher("Ewelina", "Makowska"),
        Teacher("Adrian", "Brzeziński"),
        Teacher("Maja", "Przybylska"),
        Teacher("Bartłomiej", "Kaczmarczyk"),
        Teacher("Klaudia", "Borowska"),
        Teacher("Filip", "Błaszkiewicz"),
        Teacher("Weronika", "Adamska"),
        Teacher("Antoni", "Górecki"),
        Teacher("Kamila", "Chojnacka"),
        Teacher("Konrad", "Szczepaniak"),
        Teacher("Izabela", "Kołodziejczyk"),
        Teacher("Wiktor", "Leszczyński"),
        Teacher("Emilia", "Lipińska"),
        Teacher("Dominik", "Kowal"),
        Teacher("Kinga", "Zielonka"),
        Teacher("Patryk", "Mikołajczyk"),
        Teacher("Nikola", "Wesołowska"),
        Teacher("Aleksander", "Cieślak")
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

    val groups = List(20) {
        Group(
            teacher = teachers.random(),
            students = students.shuffled().take(6).toMutableList(),
            intervalDays = 7,
            dayIndex = 0,
            timeEpoch = (16 * 3600) + (30 * 60)
        )
    }

    val lessons = buildList {
        val timeZone = TimeZone.currentSystemDefault()
        val current = Clock.System.now().toLocalDateTime(timeZone)

        fun lessonWith(epochStart: Long) = Lesson(
            epochStart = epochStart,
            topic = topics.random(),
            course = courses.random(),
            teacher1 = teachers.random(),
            teacher2 = teachers.random(),
            students = groups.random().students
        )

        fun epochStartWith(year: Int, month: Int, day: Int) = LocalDateTime(
            year,
            month,
            day,
            listOf(10, 14, 16, 20).random(),
            listOf(0, 15, 30, 45).random()
        ).toInstant(timeZone).epochSeconds

        repeat(3) { yearOffsetIndex ->
            repeat(12) { monthIndex ->
                repeat(50) { dayIndex ->
                    add(
                        lessonWith(
                            epochStartWith(
                                current.year - 1 + yearOffsetIndex, //Previous, current, next
                                1 + monthIndex,
                                1 + dayIndex / 2, //Two lessons per day
                            )
                        )
                    )
                }
            }
        }
    }
}