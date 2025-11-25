package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Group(
    val dayIndex: Long = 0,
    val epoch: Long = 0,
    val interval: Long = 7,
    val teacher: Teacher? = null,
    val students: List<Student> = listOf(),
    val uuid: String = Uuid.random().toString()
)