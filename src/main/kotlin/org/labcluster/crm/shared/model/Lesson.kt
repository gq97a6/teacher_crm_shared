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
    val teacher1: Teacher? = null,
    val teacher2: Teacher? = null,
    val students: List<Student> = listOf(),
    val uuid: String = Uuid.random().toString()
)

fun Lesson.toEntity() = LessonEntity(
    epochStart,
    epochBegin,
    duration,
    topic?.uuid,
    teacher1?.uuid,
    teacher2?.uuid,
    uuid,
)

fun LessonEntity.toModel(topic: Topic?, teacher1: Teacher?, teacher2: Teacher?, students: List<Student>) = Lesson(
    epochStart, epochBegin, duration, topic, teacher1, teacher2, students, uuid
)

fun LessonEntity.toModel(db: Database): Lesson? = runCatching {
    db.transactionWithResult {
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

        val topic = topicUuid?.let { notNullUuid ->
            db.topicQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        this@toModel.toModel(topic, teacher1, teacher2, students)
    }
}.getOrNull()