package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Lesson(
    override var epochStart: Long = 0,
    override var epochBegin: Long? = null,
    override var duration: Int = 5400,
    override var topic: Topic? = null,
    override var course: Course? = null,
    override var teacher1: Teacher? = null,
    override var teacher2: Teacher? = null,
    override var students: MutableList<Student> = mutableListOf(),
    override var attendance: MutableList<String> = mutableListOf(),
    override var uuid: String = Uuid.random().toString()
): LessonData

interface LessonData {
    var epochStart: Long
    var epochBegin: Long?
    var duration: Int
    var topic: Topic?
    var course: Course?
    var teacher1: Teacher?
    var teacher2: Teacher?
    var students: MutableList<Student>
    var attendance: MutableList<String>
    var uuid: String
}