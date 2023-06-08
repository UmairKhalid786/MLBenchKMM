package com.techlads.mlbench

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface NativeClient {
    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
}

expect fun getNetworkClient(): NativeClient