package com.example.diethub.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diethub.R
import com.example.diethub.Screen

class RankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            RankPage()
        }
    }
}

@Composable
fun RankPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFD077)) // Light orange background color
    ) {
        TopBar(navController = navController)
        Spacer(modifier = Modifier.height(12.dp))
        TopPodiumSection()
        Spacer(modifier = Modifier.height(16.dp))
        RestaurantList(
            restaurants = listOf(
                Restaurant("My Restaurant", 5.0),
                Restaurant("0 kcal Restaurant+++++++++TT", 4.9)
            )
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //홈버튼
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

        Spacer(modifier = Modifier.width(8.dp))

        // Search Bar
        val searchText = remember { mutableStateOf(TextFieldValue("")) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                colors = TextFieldDefaults.colors(Color.White),
                value = searchText.value,
                onValueChange = { searchText.value = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = { /* Search action */ }),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (searchText.value.text.isEmpty()) {
                Text(
                    text = "다른 사용자의 식당을 검색해보세요",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            }
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
            .background(Color(0xFFFFD077))
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
                    text = restaurant.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "⭐️ ${restaurant.rating} / 5.0",
                    fontSize = 14.sp,
                    color = Color.Gray
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

data class Restaurant(val name: String, val rating: Double)

// Preview Functions
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
//    RankPage()
}

@Preview(showBackground = true)
@Composable
fun TopPodiumSectionPreview() {
    TopPodiumSection()
}

@Preview(showBackground = true)
@Composable
fun RestaurantListItemPreview() {
    RestaurantListItem(restaurant = Restaurant("Preview Restaurant", 4.8))
}
