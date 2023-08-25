package com.example.composearchitecture.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    navController: NavController
){


}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(rememberNavController())
}