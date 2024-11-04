package com.example.pokeapiconsulter.ui.components;

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapiconsulter.repository.clients.responses.PokemonListItemResponse

@Composable
fun PokemonListItem(
    pokemon: PokemonListItemResponse,
    onPokemonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.125f)
            .padding(11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Card(
            onClick = onPokemonClick,
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(pokemon.name.capitalize(),
                style = TextStyle(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonItem(){
    PokemonListItem(
        pokemon = PokemonListItemResponse("Pichu", "test.com"),
        onPokemonClick = {}
    )
}
