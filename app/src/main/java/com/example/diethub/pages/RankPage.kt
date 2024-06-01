package com.example.diethub.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diethub.R
import com.example.diethub.Screen
import com.example.diethub.SearchViewModel
import com.example.diethub.api.Restaurant


@Composable
fun RankPage(navController: NavController, searchViewModel: SearchViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3A9AFC), Color(0xFFFFD077), Color(0xFFFFB36D), Color(0xFFFFB36D)
                    ), startY = 0.0f, endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        TopBar(navController = navController, searchViewModel = searchViewModel)
        TopPodiumSection()
        Spacer(modifier = Modifier.height(16.dp))
        RestaurantList(
            restaurants = searchViewModel.searchResults
        )
    }
}

@Composable
fun TopBar(navController: NavController, searchViewModel: SearchViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

        Spacer(modifier = Modifier.width(8.dp))

        val searchText = remember { mutableStateOf(TextFieldValue("")) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .shadow(4.dp, RoundedCornerShape(20.dp))
                .background(Color.White, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it;
                    searchViewModel.searchRestaurant(searchText.value.text);
                    Log.d("search", searchText.value.text)},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                decorationBox = { innerTextField ->
                    if (searchText.value.text.isEmpty()) {
                        Text(
                            text = "다른 사용자의 식당을 검색해보세요",
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun TopPodiumSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFFFFD077))
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_ranking_header),
            contentDescription = "Ranking Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(restaurants) { restaurant ->
                RestaurantListItem(restaurant)
            }
        }
    }
}

@Composable
fun RestaurantListItem(restaurant: Restaurant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = restaurant.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFFFD077)
                ), onClick = { //TODO: 클릭이벤트
                }) {
                Text(text = "팔로우")
            }
        }
    }


}




@Preview(showBackground = true)
@Composable
fun RankPagePreview() {
    val navController = rememberNavController()
    RankPage(navController)
}