package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
open class Teacher(
    val name: String = "",
    val surname: String = "",
    val uuid: String = Uuid.random().toString()
)