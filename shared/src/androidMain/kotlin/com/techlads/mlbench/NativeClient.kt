package com.techlads.mlbench

import io.ktor.client.*
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

class AndroidNativeClient : NativeClient {
    override fun httpClient(config: HttpClientConfig<*>.() -> Unit)   = HttpClient(OkHttp) {
        config(this)

        engine {
            config {
                retryOnConnectionFailure(false)
                connectTimeout(30, TimeUnit.SECONDS)
            }
        }
    }

    override fun getPlatformName() = "Android"
}

actual fun getNetworkClient(): NativeClient = AndroidNativeClient()