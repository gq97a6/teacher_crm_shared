package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Lesson.Companion.toModel

open class LessonRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Lesson? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel(db)
    }.getOrNull()

    open fun insert(list: List<Lesson>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.transaction {
            list.forEach {
                db.lessonQueries.insert(it.toEntity())
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Lesson>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.lessonQueries.selectAll().executeAsList().map { it.toModel(db) }
    }.getOrNull()
}