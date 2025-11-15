package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.LessonEntity
import kotlin.uuid.Uuid

@Serializable
data class Lesson(
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

fun Lesson.toEntity() = LessonEntity(
    epochStart,
    epochBegin,
    duration,
    topic?.uuid,
    course?.uuid,
    teacher1?.uuid,
    teacher2?.uuid,
    uuid,
)

fun LessonEntity.toModel(
    topic: Topic?,
    course: Course?,
    teacher1: Teacher?,
    teacher2: Teacher?,
    students: List<Student>,
    attendance: List<String>
) = Lesson(
    epochStart,
    epochBegin,
    duration,
    topic,
    course,
    teacher1,
    teacher2,
    students,
    attendance,
    uuid
)

fun LessonEntity.toModel(db: Database): Lesson? = runCatching {
    db.transactionWithResult {
        val topic = topicUuid?.let { notNullUuid ->
            db.topicQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val course = courseUuid?.let { notNullUuid ->
            db.courseQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel(db)
        }

        val teacher1 = teacher1Uuid?.let { notNullUuid ->
            db.teacherQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val teacher2 = teacher1Uuid?.let { notNullUuid ->
            db.teacherQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val students = db.timetableQueries
            .selectStudentsOfLesson(uuid)
            .executeAsList()
            .map { it.toModel() }

        val attendance = db.attendanceQueries
            .selectAttendeesForLesson(uuid)
            .executeAsList()
            .map { it.toModel().uuid }

        this@toModel.toModel(topic, course, teacher1, teacher2, students, attendance)
    }
}.getOrNull()