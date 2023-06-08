package com.techlads.mlbench

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", GetWeather().greet().contains("Android"))
    }
}