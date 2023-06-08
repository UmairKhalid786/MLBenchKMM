package com.techlads.mlbench

import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(GetWeather().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}