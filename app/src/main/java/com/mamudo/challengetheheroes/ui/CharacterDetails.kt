package com.mamudo.challengetheheroes.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.picasso.PicassoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun CharacterDetailsScreen(viewModel: CharacterDetailsViewModel) {
    val imageModifier = Modifier
        .preferredHeight(200.dp)
        .fillMaxWidth()
    MaterialTheme {
        Scaffold(
            backgroundColor = Color.Black,
            bodyContent = {
                ScrollableColumn {
                    Box {
                        PicassoImage(
                            data = viewModel.state.value.thumbnail.uri,
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
                        TopAppBar(
                            title = {
                                CenteredColumn {
                                    Text(text = viewModel.state.value.name)
                                }
                            },
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp,
                            contentColor = Color.White,
                            navigationIcon = {
                                IconButton(onClick = { viewModel.onBackPressed() }) {
                                    Icon(Icons.Filled.ArrowBack)
                                }
                            }
                        )
                    }
                    if (viewModel.state.value.description != "") {
                        Text(
                            text = "Details",
                            modifier = Modifier.padding(20.dp).fillMaxWidth(),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
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
                        Text(
                            text = "Comics",
                            modifier = Modifier.padding(20.dp).fillMaxWidth(),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                        Column {
                            for (i in viewModel.state.value.comics.items.takeLast(10)) {
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
                }
            }
        )
    }
}