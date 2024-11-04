package com.example.pokeapiconsulter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.pokeapiconsulter.R
import com.example.pokeapiconsulter.ui.components.PokemonListItem
import com.example.pokeapiconsulter.viewmodels.PokemonViewModel

@Composable
fun PokemonListScreen (
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel,
){
    val uiState by viewModel.uiState.collectAsState()
    val pokemons = uiState.pokemonList

    val listState = rememberLazyListState()
    val buffer = 5

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        if(reachedBottom){
          viewModel.fetchPokemons()
        }
    }


    Column (
        modifier = modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Red)
                .clipToBounds()
        )
        {
            Image(
                painter = painterResource(id = R.drawable._50px_pok_dex_logo),
                contentDescription = "Title Card",
                contentScale = ContentScale.Crop,
                modifier = modifier.height(70.dp)
            )
        }
        LazyColumn(
            state = listState,
            modifier = modifier
        ) {
            items(pokemons) {
                PokemonListItem(pokemon = it,
                    onPokemonClick = { viewModel.navigateToDetails(it.name) }
                )
            }
            item {
                if (uiState.loadingMorePokemon)
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(75.dp)
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp).padding(16.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonListScreen(){
    PokemonListScreen(viewModel = PokemonViewModel())
}