package com.example.composearchitecture.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composearchitecture.viewmodel.GithubViewModel
import androidx.navigation.compose.rememberNavController


@Composable
fun HiltScreen(
    viewModel: GithubViewModel = hiltViewModel(),
    navController: NavController
){
    LazyColumn {
        item {
            Button(onClick = {
                viewModel.getRepos()
            }) {
                Text("리포지토리 가져오기")
            }
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HiltScreenPreview(){
    HiltScreen(navController = rememberNavController())
}