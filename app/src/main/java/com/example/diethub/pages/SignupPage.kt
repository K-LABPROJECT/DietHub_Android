package com.example.diethub.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DietHubForm() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "DietHub",
            fontSize = 44.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF494949),
            modifier = Modifier.padding(bottom = 4.dp),
        )
        Text(
            text = "DietHub를 시작하기 위해 몇 가지 사항을 알려주세요",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        InputField(hint = "닉네임을 입력해주세요", unit = "cm")
        InputField(hint = "현재 키를 입력해주세요", unit = "kg")
        InputField(hint = "현재 체중을 입력해주세요", unit = "kg")
        InputField(hint = "현재 근육량을 입력해주세요", unit = "kg")
        InputField(hint = "목표중인 체중을 입력해주세요", unit = "kg")

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* TODO: 회원가입 완료 */ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF5B6BFD)
            )
        ) {
            Text(
                text = "완료",
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
    }
}

@Composable
fun InputField(hint: String, unit: String) {

    val backgroundColor = Color(0xFFE2E2E2)
    TextField(
        value = remember { mutableStateOf("") }.value,
        onValueChange = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
            .background(backgroundColor),
        label = { Text(hint, fontSize = 12.sp) },
        trailingIcon = { Text(unit, fontSize = 12.sp) },
        textStyle = TextStyle(fontSize = 12.sp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = backgroundColor,
            focusedContainerColor = backgroundColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDietHubForm() {
    DietHubForm()
}
