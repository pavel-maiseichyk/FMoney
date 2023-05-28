package com.pm.savings.domain.core.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun LocalDateTime.toEpochMillis(): Long {
        return this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun previousMonthYear(month: Int, year: Int): Pair<Int, Int> {
        return if (month == 1) {
            Pair(12, year - 1)
        } else {
            Pair(month - 1, year)
        }
    }

    fun nextMonthYear(month: Int, year: Int): Pair<Int, Int> {
        return if (month == 12) {
            Pair(1, year + 1)
        } else {
            Pair(month + 1, year)
        }
    }

    fun formatDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}