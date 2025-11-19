package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.GroupEntity
import kotlin.uuid.Uuid

@Serializable
data class Group(
    val dayIndex: Long = 0,
    val epoch: Long = 0,
    val interval: Long = 7,
    val teacher: Teacher? = null,
    val students: List<Student> = listOf(),
    val uuid: String = Uuid.random().toString()
)

fun Group.toEntity() = GroupEntity(
    dayIndex,
    epoch,
    interval,
    teacher?.uuid,
    uuid,
)

fun GroupEntity.toModel(teacher: Teacher?, students: List<Student>) = Group(
    dayIndex, epoch, interval, teacher, students, uuid
)

fun GroupEntity.toModel(db: Database): Group? = runCatching {
    db.transactionWithResult {
        val teacher = teacherUuid?.let { notNullUuid ->
            db.teacherQueries
                .selectByUuid(notNullUuid)
                .executeAsOneOrNull()
                ?.toModel()
        }

        val students = db.groupStudentQueries
            .selectStudentsOfGroup(uuid)
            .executeAsList()
            .map { it.toModel() }

        this@toModel.toModel(teacher, students)
    }
}.getOrNull()