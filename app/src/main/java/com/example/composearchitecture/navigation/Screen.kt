package com.example.composearchitecture.navigation

sealed class Screen(val route: String) {
    object Main : Screen(route = "main_screen")
    object ViewModel : Screen(route = "viewmodel_screen")
    object CompositionLocal : Screen(route = "composition_local_screen")
    object Theme : Screen(route = "theme_screen")
}