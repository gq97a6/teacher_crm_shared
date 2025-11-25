package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Topic(
    var name: String = "",
    var uuid: String = Uuid.random().toString()
)