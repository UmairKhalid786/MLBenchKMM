package com.techlads.mlbench.domain

import com.techlads.mlbench.data.ContentResponse
import com.techlads.mlbench.network.NetworkClient
import com.techlads.mlbench.network.safeGet

const val CONTENT_URL = "https://api.themoviedb.org/3/trending/all/day?language=en-US"
class GetTrendingMoviesUseCase {
    suspend operator fun invoke() = NetworkClient.httpClient.safeGet<ContentResponse>(CONTENT_URL)
}