package com.techlads.mlbench

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

class IOSNativeClient: NativeClient {
    override fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
        config(this)

        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}

actual fun getNetworkClient(): NativeClient = IOSNativeClient()