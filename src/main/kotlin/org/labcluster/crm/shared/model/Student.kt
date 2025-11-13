package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.StudentEntity
import kotlin.uuid.Uuid

@Serializable
data class Student(
    val name: String = "",
    val surname: String = "",
    val uuid: String = Uuid.random().toString()
) {
    companion object {
        fun StudentEntity.toModel() = Student(name, surname, uuid)
    }

    fun toEntity() = StudentEntity(name, surname, uuid)
}