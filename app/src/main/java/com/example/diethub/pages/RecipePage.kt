package com.example.diethub.pages

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diethub.R

@Composable
fun Recipe(navController: NavController) {
    val scrollState = rememberScrollState()
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
                        text = "샐러드 - Junior Heart",
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
                        val ingredients = listOf(
                            "토마토", "오이", "양파", "당근", "상추", "닭가슴살", "두부", "올리브", "아보카도", "드레싱"
                        )
                        items(ingredients) { ingredient ->
                            AssistChip(onClick = { /* Do something */ },
                                label = { Text(text = ingredient) })
                        }
                    }

                    Text(
                        text = """
            1. 채소 세척 및 손질
            모든 채소를 깨끗이 씻습니다.
            양상추, 시금치, 로메인 등을 먹기 좋은 크기로 뜯습니다.
            토마토는 슬라이스하거나 다이스합니다.
            오이와 당근은 얇게 슬라이스합니다.
            양파는 얇게 채썰기 합니다.
            아보카도는 반으로 잘라 씨를 제거한 후, 껍질을 벗기고 슬라이스합니다.
            
            2. 닭가슴살 또는 두부 준비 (선택 사항)
            닭가슴살을 삶아 먹기 좋은 크기로 찢습니다.
            두부는 물기를 제거하고, 먹기 좋은 크기로 자릅니다.
            
            3. 샐러드 조립
            큰 샐러드 볼에 준비된 채소들을 넣습니다.
            토마토, 오이, 당근, 양파, 올리브, 아보카도를 추가합니다.
            
            4. 드레싱 준비
            올리브 오일 3큰술, 발사믹 식초 1큰술, 소금과 후추를 넣고 잘 섞습니다.
            기호에 따라 레몬즙이나 허니를 추가해도 좋습니다.
            
            5. 샐러드에 드레싱 뿌리기
            준비한 드레싱을 샐러드에 골고루 뿌리고, 가볍게 섞습니다.
            
            6. 마무리 및 서빙
            샐러드를 접시에 담고, 올리브 오일을 약간 더 뿌려줍니다.
            """.trimIndent(), fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp)
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
    Recipe(navController = navController)
}
