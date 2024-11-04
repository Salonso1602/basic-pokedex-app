package com.example.pokeapiconsulter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AsyncImagesCarousel(sprites: List<String>, modifier: Modifier = Modifier) {
    val pageCount = sprites.size
    val pagerState = rememberPagerState { pageCount }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 16.dp,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(10.dp, 250.dp)
    ) { index ->
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = sprites[index],
                    contentDescription = "sprite",
                    modifier = Modifier
                        .defaultMinSize(150.dp, 150.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                if(index > 0)
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Previous Sprite"
                    )

                Spacer(modifier = modifier.width(100.dp))
                Text(text = "Sprite ${index+1}/$pageCount")
                Spacer(modifier = modifier.width(100.dp))

                if(index + 1 < pageCount)
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Previous Sprite"
                    )
            }
        }
    }
}