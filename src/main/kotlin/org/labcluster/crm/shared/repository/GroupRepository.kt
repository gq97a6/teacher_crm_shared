package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.toEntity
import org.labcluster.crm.shared.model.toModel

open class GroupRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Group? = runCatching {
        if (db == null) throw NullPointerException()
        return db.groupQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel(db)
    }.getOrNull()

    open fun insert(list: List<Group>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.transaction {
            list.forEach { group ->
                db.groupQueries.insert(group.toEntity())

                group.students.forEach { student ->
                    db.studentQueries.insert(student.toEntity())
                    db.groupStudentQueries.insert(group.uuid, student.uuid)
                }
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Group?>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.groupQueries.selectAll().executeAsList().map { it.toModel(db) }
    }.getOrNull()
}