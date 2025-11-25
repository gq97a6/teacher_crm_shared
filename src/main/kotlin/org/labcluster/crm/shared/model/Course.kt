package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.CourseEntity
import org.labcluster.crm.shared.Database
import kotlin.uuid.Uuid

@Serializable
open class Course(
    val name: String = "",
    val topics: List<Topic> = listOf(),
    val uuid: String = Uuid.random().toString()
)

fun Course.toEntity() = CourseEntity(name, uuid)
fun CourseEntity.toModel(topics: List<Topic>) = Course(name, topics, uuid)
fun CourseEntity.toModel(db: Database): Course = db.courseTopicQueries
    .selectTopicsOfCourse(uuid)
    .executeAsList()
    .map { it.toModel() }
    .let { this.toModel(it) }