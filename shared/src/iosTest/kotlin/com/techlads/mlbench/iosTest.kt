package com.techlads.mlbench

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(GetWeather().greet().contains("iOS"), "Check iOS is mentioned")
    }
}