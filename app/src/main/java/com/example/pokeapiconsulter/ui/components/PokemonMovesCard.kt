package com.example.pokeapiconsulter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapiconsulter.repository.clients.responses.PokemonDetailsResponse

@Composable
fun PokemonMovesCard(
    pokemon: PokemonDetailsResponse,
    backgroundCardColors: CardColors,
    statCardColors: CardColors,
    modifier: Modifier = Modifier
){
    Card(
        colors = backgroundCardColors,
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = "Moves",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    ),
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                items(pokemon.moves) {
                    Card (
                        colors = statCardColors,
                        modifier = modifier
                            .padding(10.dp)
                            .size(95.dp, 75.dp)
                    ){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier
                                .fillMaxSize()
                        ){
                            Text(
                                text = it.move.name.toUpperCase(),
                                maxLines = 1,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}