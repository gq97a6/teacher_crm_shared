package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.toEntity
import org.labcluster.crm.shared.model.toModel

open class LessonRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Lesson? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel(db)
    }.getOrNull()

    open fun insert(list: List<Lesson>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.transaction {
            list.forEach { lesson ->
                db.lessonQueries.insert(lesson.toEntity())

                lesson.students.forEach { student ->
                    db.studentQueries.insert(student.toEntity())
                    db.timetableQueries.insert(student.uuid, lesson.uuid)
                }
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Lesson?>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.selectAll().executeAsList().map { it.toModel(db) }
    }.getOrNull()

    open fun selectTimetable(studentUuid: String, db: Database? = defaultDatabase): List<Lesson?>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.timetableQueries.selectLessonsOfStudent(studentUuid).executeAsList().map { it.toModel(db) }
    }.getOrNull()
}