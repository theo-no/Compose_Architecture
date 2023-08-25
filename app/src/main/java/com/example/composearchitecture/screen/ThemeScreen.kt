package com.example.composearchitecture.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ThemeScreen(
    navController: NavController
){
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text("안녕하세요. 패스트캠퍼스")
            Text("스안녕하세요. 패스트캠퍼")
            Text("퍼스안녕하세요. 패스트캠")
            Text("캠퍼스안녕하세요. 패스트")
            Text("트캠퍼스안녕하세요. 패스")
            Text("스트캠퍼스안녕하세요. 패")
            Text("패스트캠퍼스안녕하세요.")
            Button(onClick = {}) {
                Text("버튼")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeScreenPreview(){
    ThemeScreen(rememberNavController())
}