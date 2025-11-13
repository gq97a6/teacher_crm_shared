package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.LessonEntity
import kotlin.uuid.Uuid

@Serializable
data class Lesson(
    val teacher: Teacher? = null,
    val epochStart: Long? = null,
    var epochBegin: Long? = null,
    var attendees: List<Student> = listOf(),
    val duration: Long = 5400,
    val course: Course? = null,
    val topic: Topic? = null,
    val uuid: String = Uuid.random().toString()
)

fun Lesson.toEntity() = LessonEntity(
    teacher?.uuid,
    epochStart,
    epochBegin,
    duration,
    course?.uuid,
    topic?.uuid,
    uuid,
)

fun LessonEntity.toModel(teacher: Teacher?, attendees: List<Student>, course: Course?, topic: Topic?) = Lesson(
    teacher, epochStart, epochBegin, attendees, duration, course, topic, uuid
)

fun LessonEntity.toModel(db: Database): Lesson {
    return db.transactionWithResult {
        val teacher = teacherUuid?.let { notNullUuid ->
            db.teacherQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val attendees = db.lessonStudentQueries
            .selectAttendeesForLesson(uuid)
            .executeAsList()
            .map { it.toModel() }

        val course = courseUuid?.let { notNullUuid ->
            db.courseQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel(db)
        }

        val topic = topicUuid?.let { notNullUuid ->
            db.topicQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        this@toModel.toModel(teacher, attendees, course, topic)
    }
}