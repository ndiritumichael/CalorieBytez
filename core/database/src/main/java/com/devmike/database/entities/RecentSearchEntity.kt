package com.devmike.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "recentSearchQueries",
)
data class RecentSearchEntity(
    @PrimaryKey
    val queryString: String,
    @ColumnInfo
    val queriedDate: Instant,
)
