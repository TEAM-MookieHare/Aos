package com.mookiehare.hohoi.feature.randommatching

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mookiehare.hohoi.core.design.component.HelloiChip
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.feature.randommatching.model.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
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
        User(1,"https://thumbnews.nateimg.co.kr/view610///news.nateimg.co.kr/orgImg/my/2022/09/07/202209061020303458_1.jpg","신현화","안녕하세요~ 저는 팔방미인입니다. 저와 친해지고 싶지 않으세요?","27세","서울특별시 양천구"),
        User(2,"https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png","강철","하하하 저는 강한 남자 강!철!입니다.","29세","서울특별시 영등포구"),
        User(3,"https://velog.velcdn.com/images/workspace/post/242af9e8-77be-432f-b1f3-71eff291b781/android-jetpack-header.png","곽하민","저는 짱 귀여운 강아지가 있어요. 같이 산책해요~","29세","인천광역시 제물포"),
        User(4,"https://www.iconsdb.com/icons/preview/green/android-6-xxl.png","김태양","태양같이 빛나는 남자 김태양입니다. ","29세","서울특별시 강서구"),
        User(5,"https://mblogthumb-phinf.pstatic.net/MjAxODA5MDFfMTIy/MDAxNTM1NzgyMDc5NDE4.Wi3qOK3vWmn5eGSpkJNVF8yDWJmYvHntt2XZCE60y6Yg.ncg9TnaCQmoA5SHkmfo4inEatxpeUwtPAERZn-vgNhYg.PNG.qbxlvnf11/7b5e56_d42a0c16a2e64a72b0221462c555f818-mv2.png?type=w800","이현묵","안녕하세요 저는 요리하는 걸 좋아하고 잘해요! 저랑 같이 요리하실 분 ~~ 크림파스타에 스테이크 어때요!","29세","서울특별시 마포구"),
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
    LazyRow(
        modifier = Modifier.padding(10.dp),
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

@Composable
fun RandomMatchingCard(
    elements : List<User>
){
    var order by remember { mutableStateOf( List(elements.size, init = {it}) ) }

    Box(
        Modifier
            .background(Color.Transparent)
            .padding(vertical = 50.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        elements.forEachIndexed { index, user ->
            SwipeAnimateCard(
                user = user,
                order = order[index],
                totalCount = elements.size,
                onSwipe = {
                    val newOrder = order.toMutableList()
                    Collections.rotate(newOrder, 1)

                    order = newOrder.toList()
                }
            )
        }
    }
}
@Composable
fun SwipeAnimateCard(
    user : User,
    order: Int,
    totalCount: Int,
    onSwipe: () -> Unit
) {
    val animatedScale by animateFloatAsState( targetValue = 1f - order * 0.05f )
    val animatedYOffset by animateDpAsState( targetValue = ((order + 1) * -12).dp )

    Box(
        modifier = Modifier
            .zIndex((totalCount - order).toFloat())
            .offset { IntOffset(x = 0, y = animatedYOffset.roundToPx()) }
            .scale(animatedScale)
            .swipeToBack { onSwipe() }
    ) {
        UserCard ( user = user )
    }
}

@Composable
fun UserCard(
    user : User,
) {
    Card(
        modifier = Modifier
            .height(500.dp)
            .width(350.dp),
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
                    modifier = Modifier.fillMaxWidth()
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
                // -> 2줄 이내로 입력 받을 것 이지만 maxLine =2 로 방어.
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
fun Modifier.swipeToBack(
    onSwipe: () -> Unit
): Modifier = composed {
    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    var leftSide by remember { mutableStateOf(true) }

    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)

        coroutineScope {
            while (true) {
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetY.stop()

                val velocityTracker = androidx.compose.ui.input.pointer.util.VelocityTracker()

                awaitPointerEventScope {
                    verticalDrag(pointerId) { change ->
                        val verticalDragOffset = offsetY.value + change.positionChange().y
                        val horizontalPosition = change.previousPosition.x

                        leftSide = horizontalPosition <= size.width / 2
                        val offsetXRatioFromMiddle = if (leftSide) {
                            horizontalPosition / (size.width / 2)
                        } else {
                            (size.width - horizontalPosition) / (size.width / 2)
                        }
                        val rotationalOffset =
                            java.lang.Float.max(1f, (1f - offsetXRatioFromMiddle) * 4f)

                        launch {
                            offsetY.snapTo(verticalDragOffset)
                            rotation.snapTo(if (leftSide) rotationalOffset else -rotationalOffset)
                        }

                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        change.consumePositionChange()
                    }
                }

                val velocity = velocityTracker.calculateVelocity().y
                val targetOffsetY = decay.calculateTargetValue(offsetY.value, velocity)

                if (targetOffsetY.absoluteValue <= size.height) {
                    // Not enough velocity -> reset
                    launch { offsetY.animateTo(targetValue = 0f, initialVelocity = velocity) }
                    launch { rotation.animateTo(targetValue = 0f, initialVelocity = velocity) }
                } else {
                    // Enough velocity -> fling the card to the back
                    val boomerangDuration = 600
                    val maxDistanceToFling = (size.height * 4).toFloat()
                    val maxRotations = 3
                    val EaseInOutEasing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)

                    val distanceToFling = java.lang.Float.min(
                        targetOffsetY.absoluteValue + (size.height / 2), maxDistanceToFling
                    )
                    val rotationToFling = java.lang.Float.min(
                        360f * (targetOffsetY.absoluteValue / size.height).roundToInt(),
                        360f * maxRotations
                    )
                    val rotationOvershoot = rotationToFling + 12f

                    launch {
                        rotation.animateTo(targetValue = if (leftSide) rotationToFling else -rotationToFling,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                0f at 0 with EaseInOutEasing
                                (if (leftSide) rotationOvershoot else -rotationOvershoot) at boomerangDuration - 50 with LinearOutSlowInEasing
                                (if (leftSide) rotationToFling else -rotationToFling) at boomerangDuration
                            })
                        rotation.snapTo(0f)
                    }
                    launch {
                        offsetY.animateTo(targetValue = 0f,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                -distanceToFling at (boomerangDuration / 2) with EaseInOutEasing
                                40f at boomerangDuration - 70
                            })
                    }
                    delay(100)
                    onSwipe()
                }
            }
        }
    }
        .offset { IntOffset(0, offsetY.value.roundToInt()) }
        .graphicsLayer {
            transformOrigin = TransformOrigin.Center
            rotationZ = rotation.value
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