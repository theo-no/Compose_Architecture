package com.example.composearchitecture.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composearchitecture.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation2Screen(
    navController: NavController
){
    Column {
        Text(
            "Navigation2"
        )
        Button(
            onClick = {
                navController.navigate(Screen.Navigation2.route){
                    launchSingleTop = true //singleTop 설정
                }
            }
        ) {
            Text(
                "Navigation2"
            )
        }
        Button(
            onClick = {
                navController.navigate(Screen.Navigation3.route){
                    popUpTo(Screen.Navigation.route){//Navigation 앞까지 삭제
                        inclusive = true //Navigation마저 삭제
                    }
                }
            }
        ) {
            Text(
                "Navigation3"
            )
        }
        val (args, setArgs) = remember { mutableStateOf("") }
        OutlinedTextField(
            value = args,
            onValueChange = setArgs
        )
        Button(
            onClick = {
                navController.navigate("navigation3ScreenWithArgument/$args")
                setArgs("")
            }
        ) {
            Text(
                "Navigation3로 Argument 전송"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Navigation2ScreenPreview(){
    Navigation2Screen(rememberNavController())
}