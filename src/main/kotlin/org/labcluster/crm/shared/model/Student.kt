package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.StudentEntity
import kotlin.uuid.Uuid

@Serializable
open class Student(
    val name: String = "",
    val surname: String = "",
    val uuid: String = Uuid.random().toString()
)

fun Student.toEntity() = StudentEntity(name, surname, uuid)
fun StudentEntity.toModel() = Student(name, surname, uuid)