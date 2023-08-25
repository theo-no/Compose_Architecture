package com.example.composearchitecture.navigation

sealed class Screen(val route: String) {
    object Main : Screen(route = "main_screen")
}