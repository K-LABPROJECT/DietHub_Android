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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.livedata.observeAsState
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


@Composable
fun MyPage(navController: NavHostController, viewModel: UserViewModel) {
    val userInfo by viewModel.myInfo.observeAsState()
    val characterId = userInfo?.let { it.characterProfileId }
    val followers = userInfo?.let { it.followers }
    val following = userInfo?.let { it.following }
    val weight = userInfo.let { it!!.weight }
    val height = userInfo.let { it!!.height }
    val targetWeight = userInfo.let { it!!.targetWeight }
    val firstWeight = userInfo.let { it!!.firstWeight }
    val muscleMass = userInfo.let { it!!.muscleMass }
    val daysUsed = viewModel.getDaysUsed()
//    val createdAt = userInfo?.createdAt ?: LocalDate.now()
//    val today = LocalDate.now()
//    val daysUsed = createdAt.until(today, ChronoUnit.DAYS)
    val weightloss = firstWeight - weight
    val progress = targetWeight/ weight
    val heightInMeters = height / 100.0
    val bmi = weight / (heightInMeters * heightInMeters)

    val scrollState = rememberScrollState()

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
            .padding(16.dp)
            .verticalScroll(scrollState)
        ,
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
            text = "DietHub와 $daysUsed 일동안 함께했어요!", // date 받아오면 수정
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
                painter = painterResource(id = if(characterId == 0)R.drawable.boy_character else R.drawable.img_mypage_character),
                contentDescription = "Character",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Text(
                text = "- ${weightloss}kg",
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
                text = "팔로워\n${followers}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )
            Text(
                text = "팔로잉\n${following}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
            ,
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White),
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "목표 체중까지 ${weight - targetWeight}kg 남았어요!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "내 목표 체중 ${targetWeight}kg, 내 시작 체중 ${firstWeight}kg",
                    fontSize = 12.sp,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UserInputField("키", "${height}", "cm")
                    UserInputField("현재\n몸무게", "${weight}", "kg")
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UserInputField("근육량", "${muscleMass}", "kg")
                    UserInputField("BMI", "${bmi}", "")
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
    val userInfo by viewModel.myInfo.observeAsState()
    var newHeight by remember { mutableStateOf(userInfo?.height?.toString() ?: "") }
    var newWeight by remember { mutableStateOf(userInfo?.weight?.toString() ?: "") }
    var newMuscleMass by remember { mutableStateOf(userInfo?.muscleMass?.toString() ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("스펙을 입력하세요", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newHeight,
            onValueChange = {
                newHeight = it
            },
            label = { Text("키 (cm)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newWeight,
            onValueChange = {
                newWeight = it
            },
            label = { Text("몸무게 (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newMuscleMass,
            onValueChange = {
                newMuscleMass = it
            },
            label = { Text("근육량 (kg)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val height = newHeight.toFloatOrNull() ?: userInfo?.height ?: 0f
            val weight = newWeight.toFloatOrNull() ?: userInfo?.weight ?: 0f
            val muscleMass = newMuscleMass.toFloatOrNull() ?: userInfo?.muscleMass ?: 0f

            viewModel.updateMyInfo(
                height = height,
                weight = weight,
                muscleMass = muscleMass
            )
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



