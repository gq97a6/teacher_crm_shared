import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Course.Companion.toModel
import org.labcluster.crm.shared.model.Lesson.Companion.toModel
import org.labcluster.crm.shared.model.Student.Companion.toModel
import org.labcluster.crm.shared.model.Teacher.Companion.toModel
import org.labcluster.crm.shared.model.Topic.Companion.toModel
import kotlin.test.Test
import kotlin.test.assertEquals

class RepositoriesTest {

    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    val db = Database(driver)

    init {
        Database.Schema.create(driver)

        Mock.apply {
            students.forEach { db.studentQueries.insert(it.toEntity()) }
            teachers.forEach { db.teacherQueries.insert(it.toEntity()) }
            topics.forEach { db.topicQueries.insert(it.toEntity()) }

            courses.forEach { course ->
                db.courseQueries.insert(course.toEntity())
                course.topics.forEach { topic ->
                    db.courseTopicQueries.link(topic.uuid, course.uuid)
                }
            }

            lessons.forEach { lesson ->
                db.lessonQueries.insert(lesson.toEntity())
                lesson.attendees.forEach { student ->
                    db.lessonQueries.linkStudent(lesson.uuid, student.uuid)
                }
            }
        }
    }

    @Test
    fun test() {
    }
}