package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Course(
    override var name: String = "",
    override var topics: MutableList<Topic> = mutableListOf(),
    override var uuid: String = Uuid.random().toString()
): CourseData

interface CourseData {
    var name: String
    var topics: MutableList<Topic>
    var uuid: String
}