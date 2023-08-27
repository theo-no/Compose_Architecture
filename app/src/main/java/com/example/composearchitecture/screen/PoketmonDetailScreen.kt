package com.example.composearchitecture.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composearchitecture.navigation.Screen
import com.example.composearchitecture.viewmodel.PoketmonViewModel

@Composable
fun PoketmonDetailScreen(
    poketmonId: Int = 0,
    viewModel : PoketmonViewModel = hiltViewModel(),
    navController: NavController
){
    viewModel.getPokemon(poketmonId)

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            val result = viewModel.pokemonResult
            val pokemonName = result.species.name

//            Text(poketmonName)

            AsyncImage(
                model = result.sprites.frontDefault,
                contentDescription = pokemonName,
                modifier = Modifier.size(100.dp)
            )

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("위로")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PoketmonDetailScreenPreview(){
    PoketmonDetailScreen(navController = rememberNavController())
}
