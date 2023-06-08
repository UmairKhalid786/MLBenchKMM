package com.techlads.mlbench

import io.ktor.client.*
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override fun httpClient(config: HttpClientConfig<*>.() -> Unit)   = HttpClient(OkHttp) {
        config(this)

        engine {
            config {
                retryOnConnectionFailure(false)
                connectTimeout(30, TimeUnit.SECONDS)
            }
        }
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()