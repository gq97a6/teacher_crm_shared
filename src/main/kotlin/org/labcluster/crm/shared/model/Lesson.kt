package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
class Lesson(
    val epochStart: Long? = null,
    var epochBegin: Long? = null,
    var attendees: List<Student> = listOf(),
    val duration: Long = 5400,
    val course: Course? = null,
    val topic: Topic? = null,
    val uuid: String = Uuid.random().toString()
)