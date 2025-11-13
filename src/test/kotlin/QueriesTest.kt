import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.toEntity
import org.labcluster.crm.shared.model.toModel
import kotlin.test.Test
import kotlin.test.assertEquals

class QueriesTest {

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
                    db.lessonStudentQueries.linkStudent(lesson.uuid, student.uuid)
                }
            }
        }
    }

    @Test
    fun courseQueriesTest() {
        val fetchedList = db.courseQueries.selectAll().executeAsList()
        assertEquals(Mock.courses.size, fetchedList.size)

        fetchedList.forEach { course ->
            assert(Mock.courses.any { it.uuid == course.uuid })
        }

        val random = Mock.courses.random()
        val fetched = db.courseQueries.selectByUuid(random.uuid).executeAsOneOrNull()?.toModel(db)
        println("${random.uuid} == ${fetched?.uuid}")
        assertEquals(random.uuid, fetched?.uuid)
    }

    @Test
    fun lessonQueriesTest() {
        val fetchedList = db.lessonQueries.selectAll().executeAsList()
        assertEquals(Mock.lessons.size, fetchedList.size)

        fetchedList.forEach { lesson ->
            assert(Mock.lessons.any { it.uuid == lesson.uuid })
        }

        val random = Mock.lessons.random()
        val fetched = db.lessonQueries.selectByUuid(random.uuid).executeAsOneOrNull()?.toModel(db)
        println("${random.uuid} == ${fetched?.uuid}")
        assertEquals(random.uuid, fetched?.uuid)
    }

    @Test
    fun studentQueriesTest() {
        val fetchedList = db.studentQueries.selectAll().executeAsList()
        assertEquals(Mock.students.size, fetchedList.size)

        fetchedList.forEach { student ->
            assert(Mock.students.any { it.uuid == student.uuid })
        }

        val random = Mock.students.random()
        val fetched = db.studentQueries.selectByUuid(random.uuid).executeAsOneOrNull()?.toModel()
        println("${random.uuid} == ${fetched?.uuid}")
        assertEquals(random.uuid, fetched?.uuid)
    }

    @Test
    fun teacherQueriesTest() {
        val fetchedList = db.teacherQueries.selectAll().executeAsList()
        assertEquals(Mock.teachers.size, fetchedList.size)

        fetchedList.forEach { teacher ->
            assert(Mock.teachers.any { it.uuid == teacher.uuid })
        }

        val random = Mock.teachers.random()
        val fetched = db.teacherQueries.selectByUuid(random.uuid).executeAsOneOrNull()?.toModel()
        println("${random.uuid} == ${fetched?.uuid}")
        assertEquals(random, fetched)
    }

    @Test
    fun topicQueriesTest() {
        val fetchedList = db.topicQueries.selectAll().executeAsList()
        assertEquals(Mock.topics.size, fetchedList.size)

        fetchedList.forEach { topic ->
            assert(Mock.topics.any { it.uuid == topic.uuid })
        }

        val random = Mock.topics.random()
        val fetched = db.topicQueries.selectByUuid(random.uuid).executeAsOneOrNull()?.toModel()
        println("${random.uuid} == ${fetched?.uuid}")
        assertEquals(random, fetched)
    }
}