package com.devmike.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devmike.database.baseDb.BaseDbTest
import com.devmike.database.dao.CalorieDao
import com.devmike.database.util.SampleData.onion
import com.devmike.database.util.SampleData.tomato
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalorieDaoTest : BaseDbTest() {
    private lateinit var calorieDao: CalorieDao

    @Before
    fun setUp() {
        calorieDao = db.calorieDao()
    }

    @Test
    fun `insert calorie entity`() {
        val calorieEntity = onion
        runTest {
            calorieDao.insert(calorieEntity)
            val insertedCalorie = calorieDao.getCalorieById(calorieEntity.name)
            assertNotNull(insertedCalorie)
            assertEquals(calorieEntity, insertedCalorie)
        }
    }

    @Test
    fun `insert all calorie entities`() {
        val calories = listOf(onion, tomato)
        runTest {
            calorieDao.insertAll(calories)
            val allCalories = calorieDao.getAllCalories().first()
            assertEquals(calories.size, allCalories.size)
            assertTrue(allCalories.contains(onion))
            assertTrue(allCalories.contains(tomato))
        }
    }

    @Test
    fun `get calorie entity by name - not found`() {
        val unknownName = "unknown"
        runTest {
            val calorie = calorieDao.getCalorieById(unknownName)
            assertNull(calorie)
        }
    }

    @Test
    fun `delete all calorie entities`() {
        val calories = listOf(onion, tomato)
        runTest {
            calorieDao.insertAll(calories)

            val insertedCalorie = calorieDao.getCalorieById(calories.first().name)
            assertEquals(onion, insertedCalorie)
            calorieDao.deleteAll()

            val allCalories = calorieDao.getAllCalories().first()
            assertEquals(0, allCalories.size)
        }
    }
}
