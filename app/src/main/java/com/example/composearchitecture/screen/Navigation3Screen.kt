package com.example.composearchitecture.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation3Screen(
    args: String = "",
    navController: NavController
){
    Text(
        "Navigation3 args = $args"
    )
}

@Preview(showBackground = true)
@Composable
fun Navigation3ScreenPreview(){
    Navigation2Screen(rememberNavController())
}