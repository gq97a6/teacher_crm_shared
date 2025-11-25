package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Course(
    var name: String = "",
    var topics: List<Topic> = listOf(),
    var uuid: String = Uuid.random().toString()
)