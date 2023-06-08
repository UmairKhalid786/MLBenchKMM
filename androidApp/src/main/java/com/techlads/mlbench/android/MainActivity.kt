package com.techlads.mlbench.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.mlbench.data.ContentResponse
import com.techlads.mlbench.domain.GetWeatherUseCase
import com.techlads.mlbench.network.Resource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MoviesContent()
                }
            }
        }
    }
}

@Composable
fun MoviesContent() {

    val content = remember {
        mutableStateOf<Resource<ContentResponse>>(Resource.loading())
    }

    LaunchedEffect(key1 = Unit) {
        content.value = GetWeatherUseCase().invoke()
    }

    when (content.value.status) {
        Resource.Status.SUCCESS -> {
            MoviesList(content.value.data?.results?.filter { it.title?.isNotBlank() ?: false }
                ?: emptyList())
        }

        Resource.Status.ERROR -> {
            content.value.message?.let {
                Text(text = it)
            }
        }

        Resource.Status.LOADING -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }

        else -> {}
    }
}

@Composable
fun MoviesList(contents: List<ContentResponse.Content>) {
    LazyColumn {
        items(contents) { content ->
            Text(
                text = content.title ?: "", modifier =
                Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.2f), shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MoviesContent()
    }
}
