package com.example.pokeapiconsulter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapiconsulter.ui.components.AsyncImagesCarousel
import com.example.pokeapiconsulter.ui.components.PokemonMovesCard
import com.example.pokeapiconsulter.ui.components.PokemonStatsCard
import com.example.pokeapiconsulter.viewmodels.PokemonViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel,
    pokemonId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetails(pokemonId)
    }
    val currentPokemon = uiState.pokemonDetails

    if (currentPokemon != null && ! uiState.loadingDetails) {
        val sprites = currentPokemon.sprites.getAllSprites()

        Column {
            Card(
                colors = buildCardColors(
                    color = Color(green = 200f, red = 0f, blue = 0f, alpha = 0.5f),
                    contentColor = Color.DarkGray
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box (
                    modifier = modifier.fillMaxWidth()
                )
                {
                    IconButton(
                        onClick = { viewModel.navigateToMainMenu() },
                        ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "go back"
                        )
                    }
                    Text(
                        text = currentPokemon.name.capitalize(),
                        modifier = modifier.align(Alignment.Center),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                        ),
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ){
                AsyncImagesCarousel(sprites)
            }
            Column(
                modifier = modifier.verticalScroll(state = rememberScrollState())
            ) {
                PokemonStatsCard(pokemon = currentPokemon,
                    backgroundCardColors = buildCardColors(
                        color = Color(green = 0f, red = 255f, blue = 0f, alpha = 0.5f),
                        contentColor = Color.DarkGray
                    ),
                    statCardColors = buildCardColors(
                        Color.Gray,
                        Color.White
                    )
                )
                PokemonMovesCard(
                    pokemon = currentPokemon,
                    backgroundCardColors = buildCardColors(
                        color = Color(green = 0f, red = 0f, blue = 200f, alpha = 0.5f),
                        contentColor = Color.DarkGray
                    ),
                    statCardColors = buildCardColors(
                        Color.Gray,
                        Color.White
                    ),
                )
            }
        }
    } else { //loading
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(alignment = Alignment.Center),
                color = Color.Red
            )
        }
    }
}

private fun buildCardColors(color: Color, contentColor: Color): CardColors {
    return CardColors(
        color,
        contentColor,
        color.copy(alpha = 0.2f),
        contentColor.copy(alpha = 0.2f)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailsScreen() {
    PokemonDetailsScreen(viewModel = PokemonViewModel(), pokemonId = "charizard")
}