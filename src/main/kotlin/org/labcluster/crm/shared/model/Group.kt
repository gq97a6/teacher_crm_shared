package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Group(
    override var dayIndex: Int = 0,
    override var epoch: Long = 0,
    override var interval: Int = 7,
    override var teacher: Teacher? = null,
    override var students: MutableList<Student> = mutableListOf(),
    override var uuid: String = Uuid.random().toString()
): GroupData

interface GroupData {
    var dayIndex: Int
    var epoch: Long
    var interval: Int
    var teacher: Teacher?
    var students: MutableList<Student>
    var uuid: String
}