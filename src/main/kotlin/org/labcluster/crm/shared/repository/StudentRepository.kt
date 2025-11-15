package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.toEntity
import org.labcluster.crm.shared.model.toModel

open class StudentRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Student? = runCatching {
        if (db == null) throw NullPointerException()
        return db.studentQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel()
    }.getOrNull()

    open fun insert(list: List<Student>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.studentQueries.transaction {
            list.forEach {
                db.studentQueries.insert(it.toEntity())
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Student>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.studentQueries.selectAll().executeAsList().map { it.toModel() }
    }.getOrNull()

    open fun selectAttended(lessonUuid: String, db: Database? = defaultDatabase): List<Student>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.attendanceQueries.selectAttendeesForLesson(lessonUuid).executeAsList().map { it.toModel() }
    }.getOrNull()
}