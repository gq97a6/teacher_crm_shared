package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Open
import kotlin.uuid.Uuid

@Open
@Serializable
class Teacher(
    var name: String = "",
    var surname: String = "",
    var uuid: String = Uuid.random().toString()
)