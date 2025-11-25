package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Topic(
    var name: String = "",
    var uuid: String = Uuid.random().toString()
)