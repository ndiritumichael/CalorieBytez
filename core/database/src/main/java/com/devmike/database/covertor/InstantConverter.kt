package com.devmike.database.covertor

import androidx.room.TypeConverter
import java.time.Instant

internal class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilli()
}
