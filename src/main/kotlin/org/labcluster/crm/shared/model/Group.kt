package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Group(
    var dayIndex: Long = 0,
    var epoch: Long = 0,
    var interval: Long = 7,
    var teacher: Teacher? = null,
    var students: List<Student> = listOf(),
    var uuid: String = Uuid.random().toString()
)