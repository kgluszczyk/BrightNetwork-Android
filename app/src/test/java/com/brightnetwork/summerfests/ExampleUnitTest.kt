package com.brightnetwork.summerfests

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun `test if toFestivals is not adding anything`() {
        //given
        val festivalsDTO = listOf<FestivalDTO>()

         //when
        val transformedFestivals  = festivalsDTO.toFestivals()

        //then
        assertEquals(1, transformedFestivals.size)
    }
}