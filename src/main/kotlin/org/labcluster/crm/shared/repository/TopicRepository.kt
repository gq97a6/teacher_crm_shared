package org.labcluster.crm.shared.repository

import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.model.Topic
import org.labcluster.crm.shared.model.Topic.Companion.toModel

open class TopicRepository(val defaultDatabase: Database? = null) {

    open fun selectByUuid(uuid: String, db: Database? = defaultDatabase): Topic? = runCatching {
        if (db == null) throw NullPointerException()
        return db.topicQueries.selectByUuid(uuid).executeAsOneOrNull()?.toModel()
    }.getOrNull()

    open fun insert(list: List<Topic>, db: Database? = defaultDatabase): Unit? = runCatching {
        if (db == null) throw NullPointerException()
        return db.topicQueries.transaction {
            list.forEach {
                db.topicQueries.insert(it.toEntity())
            }
        }
    }.getOrNull()

    open fun selectAll(db: Database? = defaultDatabase): List<Topic>? = runCatching {
        if (db == null) throw NullPointerException()
        return db.topicQueries.selectAll().executeAsList().map { it.toModel() }
    }.getOrNull()
}