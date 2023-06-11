package com.techlads.mlbench

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface NativeClient {
    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
    fun getPlatformName(): String
}

expect fun getNetworkClient(): NativeClient