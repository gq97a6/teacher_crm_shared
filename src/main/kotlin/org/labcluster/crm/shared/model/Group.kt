package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.GroupEntity
import kotlin.uuid.Uuid

@Serializable
data class Group(
    val teacher: Teacher? = Teacher(),
    val students: List<Student> = listOf(),
    val lessons: List<Lesson> = listOf(),
    val interval: Long? = 7,
    val dayOfWeek: Long? = null,
    val timeOfDay: Long? = null,
    val uuid: String = Uuid.random().toString()
)

fun Group.toEntity() = GroupEntity(
    teacher?.uuid,
    interval,
    dayOfWeek,
    timeOfDay,
    uuid,
)

fun GroupEntity.toModel(teacher: Teacher?, students: List<Student>, lessons: List<Lesson>) = Group(
    teacher, students, lessons, interval, dayOfWeek, timeOfDay, uuid
)

fun GroupEntity.toModel(db: Database): Group {
    return db.transactionWithResult {
        val teacher = teacherUuid?.let { notNullUuid ->
            db.teacherQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val students = db.groupStudentQueries
            .selectLinkedWith(uuid)
            .executeAsList()
            .map { it.toModel() }

        val lessons = db.groupLessonQueries
            .selectLinkedWith(uuid)
            .executeAsList()
            .map { it.toModel(db) }

        this@toModel.toModel(teacher, students, lessons)
    }
}