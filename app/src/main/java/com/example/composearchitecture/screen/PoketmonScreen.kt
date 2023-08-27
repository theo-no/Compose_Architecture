package com.example.composearchitecture.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.composearchitecture.viewmodel.PoketmonViewModel

@Composable
fun PoketmonScreen(
    viewModel : PoketmonViewModel = hiltViewModel(),
    navController: NavController
){
    val items = viewModel.pokemonList.collectAsLazyPagingItems()
    LazyColumn {
        items(items, key = {it.url}){
            it?.let {
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column {
                            Text("포케몬: ${it.name}")
                            Text(
                                text = it.url,
                                Modifier.alpha(0.4f)
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(onClick = {
                            val poketmonId = it.url.substringAfter("pokemon/")
                                .substringBefore("/")
                                .toInt()
                            navController.navigate("Detail/${poketmonId}")
                        }) {
                            Text("보기")
                        }
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PoketmonScreenPreview(){
    PoketmonScreen(navController = rememberNavController())
}