package com.example.composearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.screen.MainScreen
import com.example.composearchitecture.screen.ViewModelScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(
            route = Screen.Main.route
        ){
            MainScreen(navController)
        }
        composable(
            route = Screen.ViewModel.route
        ){
            ViewModelScreen(navController)
        }
    }
} // End of setUpNavGraph