package com.example.diethub.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diethub.R
import com.example.diethub.RecipeViewModel
import com.example.diethub.api.Recipe
import com.example.diethub.api.Restaurant

@Composable
fun Recipe(navController: NavController, recipeViewModel: RecipeViewModel, restaurantId : Int, recipeId:Int) {

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        recipeViewModel.fetchRecipe(restaurantId,recipeId)
    }
    val recipe = recipeViewModel.recipe
    Log.d("recipe", recipe.recipeName)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFBF8))
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${recipe.recipeName} - Junior Heart",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFFEDB362)  // 샐러드 텍스트 색상 변경
                    )
                    Text(
                        text = "30분",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.align(Alignment.End),
                        textAlign = TextAlign.End
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        "메인 재료",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    LazyRow(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)  // Chip 간 간격을 2dp로 설정
                    ) {
                        val ingredients = recipe.recipeIngredient.split(",")
                        items(ingredients) { ingredient ->
                            AssistChip(onClick = { /* Do something */ },
                                label = { Text(text = ingredient) })
                        }
                    }

                    Text(
                        text = recipe.recipeDetail.trimIndent(), fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePagePreview() {
    val navController = rememberNavController()
    Recipe(navController = navController,viewModel(), 1, 1)
}
