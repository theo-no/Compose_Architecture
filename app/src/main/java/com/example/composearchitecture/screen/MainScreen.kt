package com.example.composearchitecture.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: ToDoViewModel = viewModel()
){
    LazyColumn{
        items(screenList){screen->
            NavButton(
                text = screen.name
            ) {
                navController.navigate(screen.rotue)
            }
        }
    }

}

@Composable
fun NavButton(
    text : String,
    onClick : () -> Unit
){
    Button(
        onClick = onClick
    ) {
        Text(
            text = text
        )
    }
}

val screenList = mutableListOf<ScreenInfo>(
    ScreenInfo("ViewModel", Screen.ViewModel.route)
)

data class ScreenInfo(
    val name : String,
    val rotue : String
)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(rememberNavController())
}
