import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.repository.*
import kotlin.test.Test

class DatabaseTest {

    val driver = JdbcSqliteDriver("jdbc:sqlite:test.db")
    val db = Database(driver)

    @Test
    fun populateDatabase() {
        Mock.apply {
            StudentRepository(db).insert(students)
            TeacherRepository(db).insert(teachers)
            TopicRepository(db).insert(topics)
            GroupRepository(db).insert(groups)
            CourseRepository(db).insert(courses)
            LessonRepository(db).apply {
                insert(lessons)
                lessons.forEach { lesson ->
                    groups.find {
                        it.students == lesson.students
                    }?.let { group ->
                        insertGroupTimetable(group, listOf(lesson))
                    }
                }
            }
        }
    }

    @Test
    fun createDatabase() {
        Database.Schema.create(driver)
    }
}