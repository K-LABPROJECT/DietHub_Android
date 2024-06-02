package com.example.diethub.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diethub.Screen

@Composable
fun LoginPage(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(40.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "DietHub",
                fontSize = 44.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF5B6BFD),
                modifier = Modifier.padding(bottom = 16.dp),
            )
            Text(
                text = "건강한 식단조절을 위해 함께해요",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))
            Button(
                onClick = { navController.navigate(Screen.SignupPage.route)},
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFFD077)
                )
            ) {
                Text(
                    text = "카카오 계정으로 시작하기",
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                )
            }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}
