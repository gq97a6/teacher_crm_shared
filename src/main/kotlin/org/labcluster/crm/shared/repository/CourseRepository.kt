package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.toEntity
import org.labcluster.crm.shared.model.toModel

open class CourseRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Course? = runCatching {
        if (db == null) throw NullPointerException()
        return db.courseQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel(db)
    }.getOrNull()

    open fun insert(list: List<Course>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.transaction {
            list.forEach { course ->
                db.courseQueries.insert(course.toEntity())

                course.topics.forEach { topic ->
                    db.topicQueries.insert(topic.toEntity())
                    db.courseTopicQueries.link(topic.uuid, course.uuid)
                }
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Course>? = runCatching {
        if (db == null) throw NullPointerException()
        val courses = db.courseQueries.selectAll().executeAsList()
        val topicsByUuid = db.topicQueries.selectAll().executeAsList().associateBy { it.uuid }
        val linksByCourseUuid = db.courseTopicQueries.selectAll().executeAsList().groupBy { it.courseUuid }

        return courses.map { course ->
            val courseTopics = linksByCourseUuid[course.uuid]
                ?.mapNotNull { link -> topicsByUuid[link.topicUuid]?.toModel() }
                ?: emptyList()

            Course(course.name, courseTopics, course.uuid)
        }
    }.getOrNull()
}