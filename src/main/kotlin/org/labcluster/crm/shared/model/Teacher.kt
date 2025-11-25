package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Teacher(
    var name: String = "",
    var surname: String = "",
    var uuid: String = Uuid.random().toString()
)