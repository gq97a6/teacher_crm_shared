package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Group(
    var dayIndex: Int = 0,
    var timeEpoch: Long = 0,
    var intervalDays: Int = 7,
    var teacher: Teacher? = null,
    var students: MutableList<Student> = mutableListOf(),
    var uuid: String = Uuid.random().toString()
)