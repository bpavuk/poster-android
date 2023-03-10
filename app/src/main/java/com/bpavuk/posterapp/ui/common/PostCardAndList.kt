package com.bpavuk.posterapp.ui.common

import android.util.Log
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bpavuk.posterapp.R
import com.bpavuk.posterapp.model.Post
import com.bpavuk.posterapp.ui.common.viewmodels.FetchingResult
import com.bpavuk.posterapp.ui.common.viewmodels.PosterUiState

@Composable
fun PostCardsList(
    uiState: PosterUiState,
    windowWidthSizeClass: WindowWidthSizeClass,
    onReachedEnd: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val postsList = uiState.postsList
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(postsList, key = { it.id }) { post ->
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
        if (uiState.fetchingResult == FetchingResult.Error) {
            item {
                NoInternetScreen()
            }
        }
        item {
            if (postsList.isNotEmpty()) {
                LaunchedEffect(postsList) {
                    Log.d("fuckery", "last id ${postsList.last().id}")
                    onReachedEnd(postsList.last().id)
                }
            }
            Log.d("fuckery", postsList.toString())
            Spacer(modifier = Modifier.height(16.dp))
            ImageLoadingProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PostCard(post: Post, modifier: Modifier = Modifier) {
    var showFullText: Boolean by remember {
        mutableStateOf(false)
    }
    val maxLines by animateIntAsState(targetValue = if (showFullText) 1000 else 2)
    Card(modifier = modifier.clip(MaterialTheme.shapes.extraLarge)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 160.dp)
                .wrapContentHeight()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.author.profileImgUrl)
                        .build(),
                    contentDescription = post.author.userName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(32.dp)
                        .aspectRatio(1f)
                        .clip(shape = MaterialTheme.shapes.extraLarge),
                )
                Text(text = post.author.userName)
            }
            Box(modifier = Modifier
                .heightIn(100.dp, 600.dp),
                contentAlignment = Alignment.Center) {
                ImageLoadingProgressIndicator()
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
            Text(
                text = post.text,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .clickable { showFullText = !showFullText }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ImageLoadingProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, modifier = modifier)
}

@Composable
fun NoInternetScreen() {
    Text(text = stringResource(id = R.string.internet_oops))
}