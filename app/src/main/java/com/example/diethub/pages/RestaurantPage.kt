package com.example.diethub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diethub.R
import com.example.diethub.RestaurantViewModel
import com.example.diethub.Screen

@Composable
fun Restaurant(navController: NavController, viewModel: RestaurantViewModel) {
    val restaurant = viewModel.restaurant.value

    LaunchedEffect(true) {
        viewModel.loadRestaurantInfo("1") // 예시 ID
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFAEA))
    ) {
        Image(
            painter = painterResource(id = R.drawable.restaurant_top),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )

        // 홈버튼
        IconButton(
            onClick = {
                navController.navigate(route = Screen.HomePage.route) {
                    popUpTo(Screen.HomePage.route) {
                        inclusive = true
                    }
                }
            }, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_button_home),
                contentDescription = "Home", modifier = Modifier.size(48.dp)
            )
        }

        //팔로우버튼
        Button(
            colors = ButtonDefaults.buttonColors(Color((0xFFFFD077))),
            elevation = ButtonDefaults.buttonElevation(4.dp),
            onClick = { },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(14.dp)
        ) {
            Text(
                text = "팔로우",
                color = Color.White
            )
        }


        // 주인장 캐릭터
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = restaurant?.title ?: "loading...",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )


            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.boy_character),
                contentDescription = "Character",
                modifier = Modifier.size(240.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.restaurant_foods),
            contentDescription = "Foods",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(LocalConfiguration.current.screenHeightDp.dp * 2 / 3)
                .align(Alignment.BottomCenter)
        )
    }
}