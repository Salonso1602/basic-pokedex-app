package com.example.pokeapiconsulter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun PokemonStatsCard (
    pokemon: PokemonDetailsResponse,
    backgroundCardColors: CardColors,
    statCardColors: CardColors,
    modifier: Modifier = Modifier
)
{
    Card(
        colors = backgroundCardColors,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = "Stats",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp
                    ),
                )
            }
            LazyVerticalGrid(columns = GridCells.Adaptive(250.dp)) {
                item {
                    Card(
                        colors = statCardColors,
                        modifier = modifier.padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = modifier.padding(10.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = "Types: ",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(text = pokemon.getTypesStrings().toUpperCase())
                        }
                    }
                }
                item {
                    Card(
                        colors = statCardColors,
                        modifier = modifier.padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = modifier.padding(10.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = "Weight: ",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(text = "${pokemon.getWeight} Kg")
                        }
                    }
                }
                item {
                    Card(
                        colors = statCardColors,
                        modifier = modifier.padding(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = modifier.padding(10.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = "Height: ",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(text = "${pokemon.getHeight} ft")
                        }
                    }
                }

                items(pokemon.stats) {
                    Card (
                        colors = statCardColors,
                        modifier = modifier
                            .padding(10.dp)
                            .height(70.dp)
                    ){
                        Row (
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ){
                            Text(
                                text = "${it.stat.name.capitalize()}: ",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                                Column {
                                    Text(
                                        text = "Base:",
                                        style = TextStyle(
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Text(text = it.base_stat.toString())
                                }
                                Column {
                                    Text(
                                        text = "Effort:",
                                        style = TextStyle(
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Text(text = it.effort.toString())
                                }
                        }
                    }
                }
            }
        }
    }
}