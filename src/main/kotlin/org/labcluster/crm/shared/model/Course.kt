package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.CourseEntity
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Topic.Companion.toModel
import kotlin.uuid.Uuid

@Serializable
data class Course(
    val name: String = "",
    val topics: List<Topic> = listOf(),
    val uuid: String = Uuid.random().toString()
) {
    companion object {
        fun CourseEntity.toModel(topics: List<Topic>) = Course(name, topics, uuid)
        fun CourseEntity.toModel(db: Database): Course = db.courseTopicQueries
            .selectLinkedWith(uuid)
            .executeAsList()
            .map { it.toModel() }
            .let { this.toModel(it) }
    }

    fun toEntity() = CourseEntity(name, uuid)
}