package com.techlads.mlbench

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.*

class IOSNativeClient: NativeClient {
    override fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
        config(this)

        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }

    override fun getPlatformName() = "iOS"
}

actual fun getNetworkClient(): NativeClient = IOSNativeClient()