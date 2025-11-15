import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.toEntity
import kotlin.test.Test

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
                    db.courseTopicQueries.insert(topic.uuid, course.uuid)
                }
            }

            lessons.forEach { lesson ->
                db.lessonQueries.insert(lesson.toEntity())
            }
        }
    }
}