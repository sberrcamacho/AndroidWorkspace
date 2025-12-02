package com.example.artspace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.artspace.screens.ArtScreen

@Composable
fun Logic() {
    var screen by remember { mutableStateOf(1) }

    val listOfId = listOf(1,2,3)
    
    when(screen) {
        1 -> {
            ArtScreen(
                id = 1,
                image = R.drawable.starry_nights,
                title = R.string.starry_night_title,
                author = R.string.starry_night_author,
                year = R.string.starry_night_year,
                onNext = { screen = it},
                onPrevious = { screen = listOfId.size }
            )
        }
        
        2 -> {
            ArtScreen(
                id = 2,
                image = R.drawable.persistence_memory,
                title = R.string.persistence_memory_title,
                author = R.string.persistence_memory_author,
                year = R.string.persistence_memory_year,
                onNext = {screen = it},
                onPrevious = {screen = it}
            )
        }
        
        3 -> {
            ArtScreen(
                id = 3,
                image = R.drawable.the_scream,
                title = R.string.the_scream_title,
                author = R.string.the_scream_author,
                year = R.string.the_scream_year,
                onNext = {screen = listOfId[0]},
                onPrevious = {screen = it}
            )
        }
    }
}

