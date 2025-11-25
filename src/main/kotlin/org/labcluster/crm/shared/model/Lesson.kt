package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Lesson(
    var epochStart: Long = 0,
    var epochBegin: Long? = null,
    var duration: Long = 5400,
    var topic: Topic? = null,
    var course: Course? = null,
    var teacher1: Teacher? = null,
    var teacher2: Teacher? = null,
    var students: List<Student> = listOf(),
    var attendance: List<String> = listOf(),
    var uuid: String = Uuid.random().toString()
)