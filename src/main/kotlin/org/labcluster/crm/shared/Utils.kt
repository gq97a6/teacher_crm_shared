package org.labcluster.crm.shared

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.shared.model.Lesson
import kotlin.time.Instant

annotation class Open

fun Lesson.epochEnd() = this.epochStart + this.duration
fun Lesson.instantStart() = Instant.fromEpochSeconds(this.epochStart)
fun Lesson.instantEnd() = Instant.fromEpochSeconds(this.epochEnd())
fun Lesson.timeStart(timeZone: TimeZone) = this.instantStart().toLocalDateTime(timeZone)
fun Lesson.timeEnd(timeZone: TimeZone) = this.instantEnd().toLocalDateTime(timeZone)