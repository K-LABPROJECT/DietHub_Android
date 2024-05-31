package com.example.diethub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.diethub.R
import com.example.diethub.Screen
import com.example.diethub.UserViewModel
import kotlin.math.round


@Composable
fun MyPage(navController: NavHostController, viewModel: UserViewModel) {
    val progress = 0.7f // 목표 달성률 (0.7 = 70%)
    val userInfo = viewModel.userInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF77A3E4), Color(0xFFFFFAEA), Color(0xFFFFFAEA)
                    ), startY = 0.0f, endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        IconButton(onClick = {
            navController.navigate(route = Screen.HomePage.route) {
                popUpTo(Screen.HomePage.route) {
                    inclusive = true
                }
            }
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_button_home),
                contentDescription = "Home",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "DietHub와\n${userInfo.date}일동안 함께했어요!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.size(200.dp)
        ) {
            CircularProgressIndicator(
                progress = progress,
                color = Color(0xFFFFD077),
                strokeWidth = 8.dp,
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.img_mypage_character),
                contentDescription = "Character",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Text(
                text = "- ${userInfo.weightLoss}kg",
                fontSize = 14.sp,
                color = Color(0xFF77A3E4),
                modifier = Modifier
                    .width(80.dp)
                    .align(Alignment.BottomCenter)
                    .shadow(10.dp, shape = RoundedCornerShape(12.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "팔로워\n${userInfo.followers}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )
            Text(
                text = "팔로잉\n${userInfo.following}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White),
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "목표 체중까지 dddd 남았어요!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "내 목표 체중 ${userInfo.targetWeight}kg, 내 시작 체중 ${userInfo.weight}kg",
                    fontSize = 12.sp,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UserInputField("키", "${userInfo.height}", "cm")
                    UserInputField("현재\n몸무게", "${userInfo.weight}", "kg")
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UserInputField("근육량", "${userInfo.muscleMass}", "kg")
                    UserInputField("BMI", "${userInfo.bmi}", "")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate(Screen.ChangeMySpec.route) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFFFD077)
                    ),
                ) {
                    Text(
                        text = "바뀐 스펙 입력하기",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
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

@Composable
fun UserInputField(label: String, initialValue: String, unit: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.Center
        )
        TextField(
            value = remember { mutableStateOf("$initialValue $unit") }.value,
            onValueChange = {},
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 11.sp),
            modifier = Modifier.width(100.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    val navController = rememberNavController()
    MyPage(navController = navController, viewModel = UserViewModel())
}




