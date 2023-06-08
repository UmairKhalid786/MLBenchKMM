package com.techlads.mlbench

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform