package com.example.composearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composearchitecture.screen.CompositionLocalScreen
import com.example.composearchitecture.screen.HiltScreen
import com.example.composearchitecture.screen.MainScreen
import com.example.composearchitecture.screen.Navigation2Screen
import com.example.composearchitecture.screen.Navigation3Screen
import com.example.composearchitecture.screen.NavigationScreen
import com.example.composearchitecture.screen.PoketmonDetailScreen
import com.example.composearchitecture.screen.PoketmonScreen
import com.example.composearchitecture.screen.ThemeScreen
import com.example.composearchitecture.screen.ViewModelScreen

@Composable
fun NavGraph(
    navController: NavHostController
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
        composable(
            route = Screen.Poketmon.route
        ){
            PoketmonScreen(navController = navController)
        }
        composable(
            route = Screen.PoketmonDetail.route,
            //args를 String 타입이 아닌 다른 타입으로 받으려면 이렇게 해야함
            arguments = listOf(
                navArgument("poketmonId"){
                    type = NavType.IntType
                }
            )
        ){
            val poketmonId = it.arguments?.getInt("poketmonId") ?: 0
            PoketmonDetailScreen(poketmonId = poketmonId, navController = navController)
        }
    }
} // End of setUpNavGraph