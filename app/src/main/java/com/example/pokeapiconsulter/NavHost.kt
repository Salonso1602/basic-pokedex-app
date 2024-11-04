package com.example.pokeapiconsulter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokeapiconsulter.ui.screens.PokemonDetailsScreen
import com.example.pokeapiconsulter.ui.screens.PokemonListScreen
import com.example.pokeapiconsulter.viewmodels.PokemonViewModel

@Composable
fun OurNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    val context = LocalContext.current
    val pokemonViewModel = PokemonViewModel(
        navigateToMainMenu = {
            navController.navigate("MainMenu")
        },
        navigateToDetails = {
            navController.navigate("pokeDetails/$it")
        }
    )
    NavHost(navController, startDestination, modifier = modifier) {
        composable("MainMenu") {
            PokemonListScreen(viewModel = pokemonViewModel)
        }
        composable("pokeDetails/{PokeId}", arguments = listOf(
            navArgument(name = "PokeId") { type = NavType.StringType }
        )) { backStackEntry ->
            val pokeId = backStackEntry.arguments?.getString("PokeId")
            PokemonDetailsScreen(
                viewModel = pokemonViewModel,
                pokemonId = pokeId.toString())
        }

    }
}
