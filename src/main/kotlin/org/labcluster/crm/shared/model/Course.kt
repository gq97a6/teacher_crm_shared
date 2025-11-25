package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Course(
    var name: String = "",
    var topics: List<Topic> = listOf(),
    var uuid: String = Uuid.random().toString()
)