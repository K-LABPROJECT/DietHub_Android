package com.example.diethub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.diethub.R
import com.example.diethub.Screen
import com.example.diethub.UserViewModel
import kotlin.math.round

@Composable
fun MyPage(navController: NavHostController, viewModel: UserViewModel) {
    val userInfo = viewModel.userInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF0F0F0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { navController.navigate(route = Screen.HomePage.route){
            popUpTo(Screen.HomePage.route){
                inclusive = true
            }
        } }) {
            Image(
                painter = painterResource(id = R.drawable.ic_button_home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(48.dp)
            )
        }
        Text("DietHub와 ${userInfo.date}일동안 함께했어요!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333), modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(16.dp))



        Image(painter = painterResource(id = R.drawable.character1), contentDescription = "sample_character",
            modifier = Modifier.clipToBounds()
            , alignment = Alignment.Center)


        Spacer(modifier = Modifier.height(16.dp))

        Text("-${userInfo.weightLoss}kg", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xff007aff))

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("팔로워")
            Text(userInfo.followers.toString())
            Spacer(modifier = Modifier.width(32.dp))
            Text("팔로잉")
            Text(userInfo.following.toString())
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("목표 체중까지 5kg 남았어요!", fontSize = 18.sp)
        Text("내 목표 체중 ${userInfo.targetWeight}kg")

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("키")
                Text("${userInfo.height}cm")
            }
            Column {
                Text("근육량")
                Text("${userInfo.muscleMass}kg")
            }
            Column {
                Text("몸무게")
                Text("${userInfo.weight}kg")
            }
            Column {
                Text("BMI")
                Text(userInfo.bmi.toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Screen.ChangeMySpec.route) }) {
            Text("바뀐 스펙 입력하러 가기")
        }
    }
}

@Composable
fun ChangeMySpec(navController: NavHostController, viewModel: UserViewModel) {
    val userInfo = viewModel.userInfo
    var newHeight by remember { mutableStateOf(userInfo.height) }
    var newWeight by remember { mutableStateOf(userInfo.weight) }
    var newMuscleMass by remember { mutableStateOf(userInfo.muscleMass) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("스펙을 입력하세요", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newHeight.toString(),
            onValueChange = { newHeight = it.toIntOrNull() ?: newHeight },
            label = { Text("키 (cm)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newWeight.toString(),
            onValueChange = { newWeight = it.toIntOrNull() ?: newWeight },
            label = { Text("몸무게 (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newMuscleMass.toString(),
            onValueChange = { newMuscleMass = it.toFloatOrNull() ?: newMuscleMass },
            label = { Text("근육량 (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            var heightInMeters = newHeight / 100.0
            var bmi = newWeight / (heightInMeters * heightInMeters)

            viewModel.userInfo = viewModel.userInfo.copy(height = newHeight, weight = newWeight, muscleMass = newMuscleMass, bmi = round(bmi*10)/10)
            navController.popBackStack()
        }) {
            Text("저장")
        }
    }
}
