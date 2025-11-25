package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Lesson(
    val epochStart: Long = 0,
    var epochBegin: Long? = null,
    val duration: Long = 5400,
    val topic: Topic? = null,
    val course: Course? = null,
    val teacher1: Teacher? = null,
    val teacher2: Teacher? = null,
    val students: List<Student> = listOf(),
    val attendance: List<String> = listOf(),
    val uuid: String = Uuid.random().toString()
)