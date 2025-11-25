package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.TeacherEntity
import kotlin.uuid.Uuid

@Serializable
open class Teacher(
    val name: String = "",
    val surname: String = "",
    val uuid: String = Uuid.random().toString()
)

fun Teacher.toEntity() = TeacherEntity(name, surname, uuid)
fun TeacherEntity.toModel() = Teacher(name, surname, uuid)