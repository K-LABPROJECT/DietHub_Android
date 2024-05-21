package com.example.diethub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diethub.R

@Composable
fun Recipe(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (modifier = Modifier
        .fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.food),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Row(modifier= Modifier.fillMaxWidth()){
            Column {
                Text(
                    text = "샐러드",
                    fontSize = 35.sp
                )
                Text(
                    text = "Junior Heart",
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "30분",
                fontSize = 35.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier =Modifier.fillMaxWidth()){
            Text(text = "메인 재료",modifier = Modifier.padding(10.dp).align(Alignment.End))
            LazyRow {
                val ingredients = listOf("토마토", "오이", "양파", "당근", "상추","닭가슴살","두부","올리브","아보카도", "드레싱")

                items(ingredients) { ingredient ->
                    Text(text = ingredient, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)) {
            Text(" 1. 채소 세척 및 손질\n" +
                    "모든 채소를 깨끗이 씻습니다.\n" +
                    "양상추, 시금치, 로메인 등을 먹기 좋은 크기로 뜯습니다.\n" +
                    "토마토는 슬라이스하거나 다이스합니다.\n" +
                    "오이와 당근은 얇게 슬라이스합니다.\n" +
                    "양파는 얇게 채썰기 합니다.\n" +
                    "아보카도는 반으로 잘라 씨를 제거한 후, 껍질을 벗기고 슬라이스합니다.\n" +
                    "2. 닭가슴살 또는 두부 준비 (선택 사항)\n" +
                    "닭가슴살을 삶아 먹기 좋은 크기로 찢습니다. 또는 프라이팬에 구워서 사용해도 좋습니다.\n" +
                    "두부는 물기를 제거하고, 먹기 좋은 크기로 자릅니다. 프라이팬에 구워서 사용해도 좋습니다.\n" +
                    "3. 샐러드 조립\n" +
                    "큰 샐러드 볼에 준비된 채소들을 넣습니다.\n" +
                    "토마토, 오이, 당근, 양파, 올리브, 아보카도를 추가합니다.\n" +
                    "준비한 닭가슴살 또는 두부를 추가합니다.\n" +
                    "4. 드레싱 준비\n" +
                    "작은 볼에 올리브 오일 3큰술, 발사믹 식초 1큰술, 소금과 후추를 넣고 잘 섞습니다.\n" +
                    "기호에 따라 레몬즙이나 허니를 추가해도 좋습니다.\n" +
                    "5. 샐러드에 드레싱 뿌리기\n" +
                    "준비한 드레싱을 샐러드에 골고루 뿌리고, 가볍게 섞습니다.\n" +
                    "드레싱이 채소에 잘 묻게 조심스럽게 버무립니다.\n" +
                    "6. 마무리 및 서빙\n" +
                    "샐러드를 접시에 담고, 올리브 오일을 약간 더 뿌려줍니다.\n" +
                    "기호에 따라 파마산 치즈나 견과류를 추가해도 좋습니다.\n" +
                    "바로 서빙하여 신선한 샐러드를 즐깁니다.")
        }
    }
}