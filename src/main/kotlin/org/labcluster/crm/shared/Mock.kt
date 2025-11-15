package org.labcluster.crm.shared

import org.labcluster.crm.shared.model.*
import java.time.LocalDateTime
import java.time.ZoneOffset

object Mock {
    val students = listOf(
        Student("Jon", "Doe"),
        Student("Mary Anne", "Smith"),
        Student("Juan Carlos", "Garcia"),
        Student("Li", "Wang"),
        Student("Fatima", "Khan"),
        Student("John Jacob", "Jingleheimer"),
        Student("Anna", "Kowalski")
    )

    val teachers = listOf(
        Teacher("Jon", "Doe"),
        Teacher("Mary Anne", "Smith"),
        Teacher("Juan Carlos", "Garcia"),
        Teacher("Li", "Wang"),
        Teacher("Fatima", "Khan"),
        Teacher("John Jacob", "Jingleheimer"),
        Teacher("Anna", "Kowalski")
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
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Web Development Fundamentals",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Database Management",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Data Structures & Algorithms",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Cloud Computing Essentials",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Cybersecurity Basics",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "DevOps Fundamentals",
            topics = topics.shuffled().take(15)
        ),
        Course(
            name = "Introduction to Machine Learning",
            topics = topics.shuffled().take(15)
        )
    )

    val lessons = List(20) {
        Lesson(
            epochStart = LocalDateTime.of(2025, 1, it + 1, 16, 0).toEpochSecond(ZoneOffset.UTC),
            topic = topics.random(),
            course = courses.random(),
            teacher1 = teachers.random(),
            teacher2 = teachers.random(),
            students = students.shuffled().take(10)
        )
    }

    val groups = List(20) {
        Group(
            teacher = teachers.random(),
            students = students.shuffled().take(6),
            interval = 7,
            day = 0,
            time = (16 * 3600) + (30 * 60)
        )
    }
}