package com.apdallahy3.basearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.apdallahy3.basearch.data.source.local.NearbyDatabse
import com.apdallahy3.basearch.data.source.local.daos.PlacesDao
import com.apdallahy3.basearch.data.source.local.entities.PlacesEntitiy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class DatabaseTest : KoinTest {
    private lateinit var placesDao: PlacesDao
    val placesDb: NearbyDatabse by inject()
    @Before
    fun initDao() {
        //switch between memory db module and local db befor run test
        loadKoinModules(roomTestModule)
        placesDao = placesDb.getPlacesDao()

    }

    @Test
    fun testSaveAndRetrive() {
        val places = listOf(
            PlacesEntitiy("1", "TestTitle", "TestAddress"),
            PlacesEntitiy("2", "TestTitle2", "TestAddress2")
        )

        // Save entities
        placesDao.inserPlaces(places)

        // Request Entities
        val savedPlaces = placesDao.getPlacesList()
        System.out.println(savedPlaces!!.size.toString())

        // compare result
        Assert.assertEquals(places, savedPlaces)
    }

    @After
    fun after() {
        placesDb.close()
        stopKoin()
    }

}