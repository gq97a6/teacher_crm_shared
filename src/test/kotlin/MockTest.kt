import org.labcluster.crm.shared.SharedMock
import kotlin.test.Test

class MockTest {

    @Test
    fun mockInitTest() {
        val mock = SharedMock()
        println(mock.students)
        println(mock.teachers)
        println(mock.topics)
        println(mock.courses)
        println(mock.groups)
        println(mock.lessons)
        println(mock.groupLessons)
    }
}