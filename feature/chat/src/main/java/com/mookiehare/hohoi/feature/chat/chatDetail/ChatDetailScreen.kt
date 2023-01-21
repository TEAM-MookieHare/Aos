package com.mookiehare.hohoi.feature.chat.chatDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mookiehare.hohoi.core.design.component.ImageProfile
import com.mookiehare.hohoi.core.design.extensions.drawNinePatch
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.feature.chat.R
import com.mookiehare.hohoi.feature.chat.model.*

val myTextMessageDummyData = MyText(
    id = "",
    content = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    isDelete = false,
    isReport = false,
    sendTime = "오후 1:24"
)

val otherTextMessageDummyData = OtherText(
    id = "",
    nickname = "곽하민",
    profileUrl = "https://static01.nyt.com/images/2014/08/24/arts/24GRANDE1/24JPGRANDE1-superJumbo.jpg",
    content = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    isDelete = false,
    isReport = false,
    sendTime = "오후 1:24"
)

@Composable
internal fun ChatDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {

    val chatDetailId by viewModel.chatDetailId.collectAsStateWithLifecycle()

    ChatDetailScreen(
        modifier = modifier,
        chatDetailId
    )
}

@Composable
internal fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    chatDetailId: String
) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Message(myTextMessageDummyData)
        }
        item {
            Message(otherTextMessageDummyData)
        }
    }
}

@Composable
fun Message(
    message: Message
){
    Row(
        horizontalArrangement = if(message is FromMy) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        if(message is FromOther) {
            ImageProfile(profileUrl = message.profileUrl)
            Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))
        }

        AuthorAndTextMessage(message = message)
    }
}

@Composable
fun AuthorAndTextMessage(
    message: Message
){
    Column {
        when(message) {
            is FromOther -> {
                Text(
                    text = message.nickname,
                    fontSize = ChatComponentStyle.normalFontSize
                )

                Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))

                when (message) {
                    is OtherText -> OtherTextMessageBubble(message = message)
                }
            }

            is FromMy -> {
                when (message) {
                    is MyText -> MyTextMessageBubble(message = message)
                }
            }
        }
    }
}

private val OtherChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
private val MyChatBubbleShape = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)

@Composable
fun MyTextMessageBubble(
    message: MyText,
){
    Row(verticalAlignment = Alignment.Bottom) {
        Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))

        Text(
            text = message.sendTime,
            fontSize = ChatComponentStyle.smallFontSize,
        )

        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            shape = MyChatBubbleShape
        ) {
            Text(
                text = message.content,
                modifier = Modifier
                    .widthIn(max = ChatComponentStyle.maxWidth)
                    .padding(16.dp, 8.dp, 8.dp, 8.dp),
                style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
            )
        }
    }
}

@Composable
fun OtherTextMessageBubble(
    message: OtherText,
){
    Row(verticalAlignment = Alignment.Bottom) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = OtherChatBubbleShape
        ) {
            Text(
                text = message.content,
                modifier = Modifier
                    .widthIn(max = ChatComponentStyle.maxWidth)
                    .padding(16.dp, 8.dp, 8.dp, 8.dp),
                style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
            )
        }

        Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))

        Text(
            text = message.sendTime,
            fontSize = ChatComponentStyle.smallFontSize,
        )
    }
}

private object ChatComponentStyle {
    val smallFontSize = 10.sp
    val normalFontSize = 12.sp
    val mediumFontSize = 14.sp
    val lineHeight = 20.sp
    val smallSpacing = 2.dp
    val mediumSpacing = 4.dp
    val maxWidth = 210.dp
}

@Preview(showBackground = true)
@Composable
fun OtherTextMessagePreview(){
    HohoiTheme {
        Message(otherTextMessageDummyData)
    }
}

@Preview(showBackground = true)
@Composable
fun MyTextMessagePreview(){
    HohoiTheme {
        Message(myTextMessageDummyData)
    }
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    HohoiTheme {
        ChatDetailScreen(chatDetailId = "1")
    }
}