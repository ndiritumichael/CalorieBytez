package com.devmike.database.baseDb

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.devmike.database.CalorieDatabase
import com.devmike.database.util.MainCoroutineRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseDbTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()
    lateinit var db: CalorieDatabase

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db =
            Room.inMemoryDatabaseBuilder(
                context,
                CalorieDatabase::class.java,
            )
                .allowMainThreadQueries()
                .build()
    }

    @After
    open fun tearDownDb() {
        db.clearAllTables()
        db.close()
    }
}
