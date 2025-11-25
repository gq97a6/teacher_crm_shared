package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Course(
    val name: String = "",
    val topics: List<Topic> = listOf(),
    val uuid: String = Uuid.random().toString()
)