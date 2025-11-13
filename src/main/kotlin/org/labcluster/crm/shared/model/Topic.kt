package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.TopicEntity
import kotlin.uuid.Uuid

@Serializable
data class Topic(
    val name: String = "",
    val uuid: String = Uuid.random().toString()
)

fun Topic.toEntity() = TopicEntity(name, uuid)
fun TopicEntity.toModel() = Topic(name, uuid)