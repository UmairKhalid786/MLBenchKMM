package com.techlads.mlbench.network

import com.techlads.mlbench.getNetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// TODO: Don't push this to git
const val IMDB_TOKEN = ""

object NetworkClient {
    private val client = getNetworkClient()

    val httpClient = client.httpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("Logger Ktor => $message")
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("Response :${response}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Any)
            header("platform",  client.getPlatformName())
        }
    }
}


suspend inline fun <reified T> HttpClient.safeGet(url: String): Resource<T> {
    return try {
        get(url){
            headers {
                this.append(HttpHeaders.Authorization, "Bearer $IMDB_TOKEN")
            }
        }.toResponse()
    } catch (e: Exception) {
        if (e.message?.contains("unable to resolve host", true) == true) {
            Resource.error(e)
        } else {
            Resource.error(Exception("Something went wrong"))
        }
    } catch (e: Throwable) {
        Resource.error(e)
    }
}


suspend inline fun <reified T> HttpResponse.toResponse(): Resource<T> {
    return when (status.value) {
        in 200..226 -> Resource.success(body())
        else -> {
            Resource.error(
                Exception("Something went wrong")
            )
        }
    }
}