package com.example.composearchitecture.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.navigation.Screen

@Composable
fun NavigationScreen(
    navController: NavController
){
    Column {
        Text(
            "Navigation"
        )
        Button(
            onClick = {
                navController.navigate(Screen.Navigation2.route){
//                    popUpTo(Screen.Main.route){
////                        inclusive = true //Main조차 같이 제거
//                    }
                }
            }
        ){
            Text(
                "Navigation2"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavigationScreenPreview(){
    NavigationScreen(rememberNavController())
}