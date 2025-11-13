package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Teacher
import org.labcluster.crm.shared.model.Teacher.Companion.toModel

open class TeacherRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Teacher? = runCatching {
        if (db == null) throw NullPointerException()
        return db.teacherQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel()
    }.getOrNull()

    open fun insert(list: List<Teacher>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.teacherQueries.transaction {
            list.forEach {
                db.teacherQueries.insert(it.toEntity())
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Teacher>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.teacherQueries.selectAll().executeAsList().map { it.toModel() }
    }.getOrNull()
}