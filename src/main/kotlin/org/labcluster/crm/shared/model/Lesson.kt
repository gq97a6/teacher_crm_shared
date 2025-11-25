package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Lesson(
    var epochStart: Long = 0,
    var epochBegin: Long? = null,
    var duration: Int = 5400,
    var topic: Topic? = null,
    var course: Course? = null,
    var teacher1: Teacher? = null,
    var teacher2: Teacher? = null,
    var students: MutableList<Student> = mutableListOf(),
    var attendance: MutableList<String> = mutableListOf(),
    var uuid: String = Uuid.random().toString()
)