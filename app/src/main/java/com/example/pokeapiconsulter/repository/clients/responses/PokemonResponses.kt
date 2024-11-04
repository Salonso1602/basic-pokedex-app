package com.example.pokeapiconsulter.repository.clients.responses

data class FetchAllPokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItemResponse>
)

data class PokemonListItemResponse(
    val name: String,
    val url: String
)

data class PokemonDetailsResponse(
    val moves: List<PokemonMoveDetails>,
    val height: Float,
    val weight: Float,
    val id: Int,
    val name: String,
    val sprites: PokemonSpriteItem,
    val stats: List<PokemonStatItem>,
    val types: List<PokemonTypeItem>
) {
    val getHeight get() = this.height/10
    val getWeight get() = this.weight/10
    fun getTypesStrings() : String{
        val result = mutableListOf<String>()

        for (type in this.types){
            result.add(type.type.name)
        }
        return result.joinToString(", ")
    }
}

data class PokemonTypeItem (
    val slot: Int,
    val type: PokemonTypeData
)

data class PokemonTypeData (
    val name: String
)

data class PokemonStatItem (
    val base_stat: Int,
    val effort: Int,
    val stat: PokemonStatData
)

data class PokemonStatData (
    val name: String
)

data class PokemonSpriteItem (
    val back_default:String?,
    val back_female:String?,
    val back_shiny:String?,
    val back_shiny_female:String?,
    val front_default:String?,
    val front_female:String?,
    val front_shiny:String?,
    val front_shiny_female: String?,
){
    fun getAllSprites(): List<String> {
        return listOfNotNull(
            this.front_default,
            this.back_default,
            this.front_female,
            this.back_female,
            this.front_shiny,
            this.back_shiny,
            this.front_shiny_female,
            this.back_shiny_female
        )
    }
}

data class PokemonMoveDetails (
    val move: PokemonMoveItem,
)

data class PokemonMoveItem (
    val name: String,
)
