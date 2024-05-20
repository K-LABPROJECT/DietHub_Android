package com.example.diethub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Restaurant(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFAEA))
    ){

        Column(
            modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.restaurant_top),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(Modifier.height(60.dp))
            Image(painter = painterResource(id = R.drawable.boy_character),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(190.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.table),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .height(400.dp),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.restaurant_foods),
            contentDescription = "restaurant_foods",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 50.dp)
                .height(330.dp)
                .clickable {navController.navigate("Recipe")},
            contentScale = ContentScale.FillBounds)
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "home button",
            modifier = Modifier.size(50.dp)
        )
    }
}
