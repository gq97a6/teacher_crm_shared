package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Topic(
    val name: String = "",
    val uuid: String = Uuid.random().toString()
)