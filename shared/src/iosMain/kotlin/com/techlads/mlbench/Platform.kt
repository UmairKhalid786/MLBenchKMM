package com.techlads.mlbench

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.*
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
        config(this)

        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}

actual fun getPlatform(): Platform = IOSPlatform()