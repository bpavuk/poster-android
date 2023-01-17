package com.bpavuk.posterapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpavuk.posterapp.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterApp(
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: PosterAppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = viewModel.uiState

    Scaffold { paddingValues ->
        PostCardsList(
            postsList = uiState.postsList,
            windowWidthSizeClass = windowWidthSizeClass,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxWidth()
        )
    }
}

@Composable
fun PostCardsList(
    postsList: List<Post>,
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(postsList) { post ->
            PostCard(
                post = post,
                modifier = when (windowWidthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        Modifier
                            .fillMaxWidth()
                            .padding(PaddingValues(horizontal = 16.dp))
                    }
                    else -> {
                        Modifier
                            .padding(PaddingValues(horizontal = 16.dp))
                            .width(600.dp)
                            .fillMaxWidth()
                    }
                }
            )
        }
    }
}

@Composable
fun PostCard(post: Post, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp, 560.dp)
                .wrapContentHeight()
        ) {
            Box(modifier = Modifier.heightIn(100.dp, 540.dp), contentAlignment = Alignment.Center) {
                ImageLoadingProgressIndicator(color = MaterialTheme.colorScheme.primary)
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(post.imgURL)
                        .build(),
                    contentDescription = post.text,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            post.text?.let { Text(text = it) }
        }
    }
}

@Composable
fun ImageLoadingProgressIndicator(color: Color, modifier: Modifier = Modifier) {
    CircularProgressIndicator(color = color, modifier = modifier)
}