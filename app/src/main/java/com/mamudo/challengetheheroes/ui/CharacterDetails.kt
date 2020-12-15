package com.mamudo.challengetheheroes.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.api.data.MarvelUrl
import com.mamudo.challengetheheroes.utils.MarvelUrlComparator
import dev.chrisbanes.accompanist.picasso.PicassoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.mamudo.challengetheheroes.R

@ExperimentalCoroutinesApi
@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel) {
    MaterialTheme {
        Scaffold(
            backgroundColor = Color.Black,
            bodyContent = {
                ScrollableColumn {
                    Header(viewModel.state.value, viewModel.onBackPressed)
                    if (viewModel.state.value.description != "") {
                        Title(text = stringResource(id = R.string.description))
                        Text(
                            text = viewModel.state.value.description,
                            modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),
                            style = TextStyle(
                                fontSize = TextUnit(100),
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    if (viewModel.state.value.comics.items.isNotEmpty()) {
                        Title(text = stringResource(id = R.string.comics))
                        Comics(viewModel.state.value.comics.items)
                    }
                }
            }
        )
    }
}

@Composable
private fun Header(c: Character, onBackPressed: () -> Unit) {
    val imageModifier = Modifier
        .preferredHeight(200.dp)
        .fillMaxWidth()
    Box {
        PicassoImage(
            data = c.thumbnail.uri,
            contentScale = ContentScale.Crop,
            loading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            error = {
                Icon(Icons.Filled.Face)
            },
            modifier = imageModifier
        )
        TopAppBar(
            title = {
                CenteredColumn {
                    Text(text = c.name)
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { onBackPressed }) {
                    Icon(Icons.Filled.ArrowBack)
                }
            }
        )
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(20.dp).fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
private fun Comics(comics: List<MarvelUrl>) {
    Column {
        for (i in comics.sortedWith(MarvelUrlComparator()).take(10)) {
            ListItem(
                text = {
                    Text(text = i.name, style = TextStyle(color = Color.White))
                },
                icon = {
                    Icon(Icons.Filled.PlayArrow, tint = Color.White)
                },
                modifier = Modifier.padding(5.dp)
            )
            Divider(color = Color.DarkGray)
        }
    }
}