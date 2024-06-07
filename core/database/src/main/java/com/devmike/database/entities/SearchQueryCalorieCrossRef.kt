package com.devmike.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    primaryKeys = ["queryString", "name"],
    tableName = "search_query_calorie_cross_ref",
    indices = [
        Index(value = ["queryString"]),
        Index(value = ["name"]),
    ],
)
data class SearchQueryCalorieCrossRef(
    val queryString: String,
    val name: String,
)

data class SearchQueryWithCalories(
    @Embedded val searchQuery: RecentSearchEntity,
    @Relation(
        parentColumn = "queryString",
        entityColumn = "name",
        associateBy = Junction(SearchQueryCalorieCrossRef::class),
    )
    val calories: List<CalorieEntity>,
)
