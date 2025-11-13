package org.labcluster.crm.shared.model

import kotlinx.serialization.Serializable
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.LessonEntity
import org.labcluster.crm.shared.model.Course.Companion.toModel
import org.labcluster.crm.shared.model.Student.Companion.toModel
import org.labcluster.crm.shared.model.Topic.Companion.toModel
import kotlin.uuid.Uuid

@Serializable
data class Lesson(
    val epochStart: Long? = null,
    var epochBegin: Long? = null,
    var attendees: List<Student> = listOf(),
    val duration: Long = 5400,
    val course: Course? = null,
    val topic: Topic? = null,
    val uuid: String = Uuid.random().toString()
) {
    companion object {
        fun LessonEntity.toModel(
            attendees: List<Student>,
            course: Course?,
            topic: Topic?
        ) = Lesson(
            epochStart,
            epochBegin,
            attendees,
            duration,
            course,
            topic,
            uuid
        )

        fun LessonEntity.toModel(db: Database): Lesson {
            return db.transactionWithResult {
                val attendees = db.lessonQueries
                    .selectAttendeesForLesson(uuid)
                    .executeAsList()
                    .map { it.toModel() }

                val course = if (courseUuid != null) db.courseQueries
                    .selectByUuid(courseUuid ?: "")
                    .executeAsOneOrNull()
                    ?.toModel(db)
                else null

                val topic = if (topicUuid != null) db.topicQueries
                    .selectByUuid(topicUuid ?: "")
                    .executeAsOneOrNull()
                    ?.toModel()
                else null

                this@toModel.toModel(attendees, course, topic)
            }
        }
    }

    fun toEntity() = LessonEntity(
        uuid,
        epochStart,
        epochBegin,
        duration,
        course?.uuid,
        topic?.uuid
    )
}