package com.mookiehare.hohoi.feature.randommatching

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mookiehare.hohoi.core.design.component.HelloiChip
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.feature.randommatching.model.User
import java.util.*
import com.mookiehare.hohoi.core.design.component.Chip


@Composable
internal fun RandomMatchingRoute(
    modifier: Modifier = Modifier,
    viewModel: RandomMatchingViewModel = hiltViewModel()
) {

    RandomMatchingScreen(
        modifier = modifier
    )
}
val chipsDummyData  =
    listOf(
        Chip("남자", mutableStateOf(value = false)),
        Chip("여자", mutableStateOf(value = false)),
        Chip("애견", mutableStateOf(value = false)),
        Chip("독서", mutableStateOf(value = false)),
        Chip("애견", mutableStateOf(value = false)),
        Chip("외국인", mutableStateOf(value = false)),
        Chip("산책", mutableStateOf(value = false)),
        Chip("맛집탐방", mutableStateOf(value = false))
    )
val usersDummyData =
    listOf(
        User(1,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH_z--yRvoZXEihHIsAJ6yux5UecyBcX8lew&usqp=CAU","신현화","안녕하세요~ 저는 팔방미인입니다. 저와 친해지고 싶지 않으세요?","27세","서울특별시 양천구"),
        User(2,"","강철","하하하 저는 강한 남자 강!철!입니다.","29세","서울특별시 영등포구"),
        User(3,"","곽하민","저는 짱 귀여운 강아지가 있어요. 같이 산책해요~","27세","인천광역시 제물포"),
        User(4,"","김태양","태양같이 빛나는 남자 김태양입니다. ","27세","서울특별시 강서구"),
        User(5,"","이현묵","안녕하세요 저는 요리하는 걸 좋아하고 잘해요! 저랑 같이 요리하실 분 ~~ 크림파스타에 스테이크 어때요!","32세","서울특별시 마포구"),
    )

@Composable
internal fun RandomMatchingScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        RandomMatchingChips(elements = chipsDummyData)
        RandomMatchingCard(elements = usersDummyData)
    }
}

@Composable
fun RandomMatchingChips(
    elements : List<Chip>
){
    Surface(
        color = Color.Transparent
    ) {
        LazyRow(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(elements.size){ index ->
                HelloiChip(
                    selected = elements[index].isSelected.value,
                    onSelectedChange = { checked -> elements[index].isSelected.value = checked }
                ) {
                    Text(text = elements[index].text)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RandomMatchingCard(
    elements : List<User>
) {
    val lazyListState = rememberLazyListState()

    Box(
        Modifier
            .background(Color.Transparent)
            .padding(vertical = 50.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier.padding(20.dp),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            items(elements.size, key = { it }) { index ->
                UserCard(user = elements[index])
            }
        }
    }
}

@Composable
fun UserCard(
    user : User,
) {
    Card(
        modifier = Modifier
            .height(500.dp)
            .width(350.dp)
            .padding(start= 10.dp, end= 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //user image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.img)
                        .crossfade(true)
                        .build()
                    ,
                    contentDescription = "user id = ${user.id}'s image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(350.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, start = 10.dp),
                ) {
                    //user name
                    Text(
                        text = user.name,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    )
                    //user age
                    Text(
                        text = user.age,
                        modifier = Modifier
                            .padding(top = 4.dp, start = 10.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
                }
                //user simple introduce
                // -> 2줄 이내로 입력 받을 것 이지만 maxLine =2  처리.
                Text(
                    text = user.introduce,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                //user area
                Text(
                    text = user.area,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 7.dp, start = 10.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun RandomMatchingPreview() {
    HohoiTheme {
        RandomMatchingScreen()
    }
}