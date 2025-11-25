package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Group(
    var dayIndex: Int = 0,
    var epoch: Long = 0,
    var interval: Int = 7,
    var teacher: Teacher? = null,
    var students: List<Student> = listOf(),
    var uuid: String = Uuid.random().toString()
)