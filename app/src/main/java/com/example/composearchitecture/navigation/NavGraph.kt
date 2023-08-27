package com.example.composearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.screen.CompositionLocalScreen
import com.example.composearchitecture.screen.HiltScreen
import com.example.composearchitecture.screen.MainScreen
import com.example.composearchitecture.screen.Navigation2Screen
import com.example.composearchitecture.screen.Navigation3Screen
import com.example.composearchitecture.screen.NavigationScreen
import com.example.composearchitecture.screen.ThemeScreen
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
        composable(
            route = Screen.CompositionLocal.route
        ){
            CompositionLocalScreen(navController)
        }
        composable(
            route = Screen.Theme.route
        ){
            ThemeScreen(navController)
        }
        composable(
            route = Screen.Navigation.route
        ){
            NavigationScreen(navController)
        }
        composable(
            route = Screen.Navigation2.route
        ){
            Navigation2Screen(navController)
        }
        composable(
            route = Screen.Navigation3.route
        ){
            Navigation3Screen(navController = navController)
        }
        composable(
            "navigation3ScreenWithArgument/{userId}"
        ){
            val userId = it.arguments?.getString("userId")
            Navigation3Screen(
                userId ?: "",
                navController = navController
            )
        }
        composable(
            route = Screen.Hilt.route
        ){
            HiltScreen(navController = navController)
        }
    }
} // End of setUpNavGraph