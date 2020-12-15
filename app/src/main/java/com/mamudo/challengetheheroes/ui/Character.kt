package com.mamudo.challengetheheroes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.mamudo.challengetheheroes.api.data.Character
import dev.chrisbanes.accompanist.picasso.PicassoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun LatestNewsScreen(viewModel: CharactersViewModel) {
    MaterialTheme {
        Scaffold(
            backgroundColor = Color.Black,
            topBar = {
                TopAppBar(title = {
                    CenteredColumn {
                        Text(text = "Marvel Characters")
                    }
                }, backgroundColor = Color.Black, elevation = 10.dp, contentColor = Color.White)
            },
            bodyContent = {
                LatestNewsFeed(viewModel = viewModel)
            }
        )
    }
}


@ExperimentalCoroutinesApi
@Composable
fun LatestNewsFeed(viewModel: CharactersViewModel) {
    val state = viewModel.state.collectAsState()
    val lastIndex = state.value.characters.lastIndex
    if (state.value.characters.isEmpty() && state.value.exception == null) {
        CenteredColumn {
            CircularProgressIndicator()
        }
    } else if (state.value.exception != null) {
        CenteredColumn {
            Text("Something went wrong.")
        }
    } else {
        LazyColumnForIndexed(items = state.value.characters) { i, item ->
            if (lastIndex == i) {
                onActive {
                    viewModel.moreCharacters()
                }
            }
            CharacterCard(item) {
                viewModel.onCharacterClick(item)
            }
        }
    }
}


@Composable
fun CharacterCard(item: Character, onClick: () -> Unit) {
    Card(
        backgroundColor = Color.DarkGray,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        val imageModifier = Modifier
            .preferredHeight(180.dp)
            .fillMaxWidth()
        Box(modifier = Modifier.fillMaxWidth()) {
            PicassoImage(
                data = item.thumbnail.uri,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Text(text = "Image cannot loaded.")
                },
                modifier = imageModifier
            )
            Text(
                item.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = TextUnit(60),
                    shadow = Shadow(blurRadius = 0.5f, offset = Offset(1f, 1f))
                ),
                modifier = Modifier.align(
                    Alignment.Center
                ),
            )

        }
    }
}

@Composable
inline fun CenteredColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}