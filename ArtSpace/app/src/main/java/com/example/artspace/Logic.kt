package com.example.artspace

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.artspace.screens.ArtScreen

@Composable
fun Logic(windowSize: WindowWidthSizeClass) {
    var screen by remember { mutableIntStateOf(1) }

    val listOfIndex = remember {  listOf(1, 2, 3) }

    when (screen) {

        1 -> ArtScreen(
            id = 1,
            image = R.drawable.starry_nights,
            title = R.string.starry_night_title,
            author = R.string.starry_night_author,
            year = R.string.starry_night_year,
            onNext = { screen = it },
            onPrevious = { screen = listOfIndex.lastIndex }
        )

        2 -> ArtScreen(
            id = 2,
            image = R.drawable.persistence_memory,
            title = R.string.persistence_memory_title,
            author = R.string.persistence_memory_author,
            year = R.string.persistence_memory_year,
            onNext = { screen = it },
            onPrevious = { screen = it }
        )


        3 -> ArtScreen(
            id = 3,
            image = R.drawable.the_scream,
            title = R.string.the_scream_title,
            author = R.string.the_scream_author,
            year = R.string.the_scream_year,
            onNext = { screen = it },
            onPrevious = { screen = it }
        )

        4 -> ArtScreen(
            id = 4,
            image = R.drawable.mona_lisa,
            title = R.string.mona_lisa_title,
            author = R.string.mona_lisa_author,
            year = R.string.mona_lisa_year,
            onNext = { screen = listOfIndex[0]},
            onPrevious = {screen = it}
        )
    }


}


