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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mookiehare.hohoi.core.design.component.ImageProfile
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.feature.chat.model.*

val myTextMessageDummyData = Text(
    id = "",
    content = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    sendTime = "오후 1:24",
    from = From.My
)

val otherTextMessageDummyData = Text(
    id = "",
    content = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    sendTime = "오후 1:24",
    from = From.Other(
        nickname = "곽하민",
        profileUrl = "https://static01.nyt.com/images/2014/08/24/arts/24GRANDE1/24JPGRANDE1-superJumbo.jpg",
    )
)

val myReplyForTextMessageDummyData = ReplyText(
    id = "",
    replyId = "",
    replyNickname = "곽하민",
    replyContent = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    content = "ㅎㅇㅎㅇ",
    sendTime = "오후 1:24",
    from = From.My
)

val otherReplyForTextMessageDummyData = ReplyText(
    id = "",
    replyId = "",
    replyNickname = "강철",
    replyContent = "꽃이 예쁘게 피었네요.) ㅁㄴㅇㄹㅁㄴㅇㄹasdflkajsdlkfjasldkjflaksjdfasdflaksjdflkjasldkfjlaksjdfㅁㄴㅇ리ㅏㅁ너이ㅏ러ㅣㅏㅁ너이ㅏ러 ㅁ니ㅏ어리ㅏㅁ넝리ㅏㅓㅁ니ㅏ어리마ㅓㄴㅁ니ㅏ어리ㅏㅁ너이ㅏ러 만어ㅣ라ㅓㅁ니ㅏㅁㄴㅇ람,눙,룸,느울,ㅜㅁ미넝리머니아러ㅣㅏㅁ너ㅣ라ㅓㅣ마너 이라ㅓㅁ니ㅏㅇ ㅓ린마ㅓㅇ ㅣ럼니 ㅏㅓ리ㅏㅁ너 ㅣ라ㅓㅁ니아 ㅓ림나ㅓ ㅣ라머니 아러ㅣㅁ나ㅓㅇ ㅣ라머닝 ㅏㅓ림ㄴ ㅏ어리ㅏ너 ㅣㅇ라ㅓ미 나어리 ㅏㅓㅁ니아 ㅓㅣㅁ나ㅓㅇㄹ ㅣㅏ머니 아러미나ㅓㅇ 림너 아ㅣㄹ머니 ㅏㅇ러ㅣㅁ ㅏ넝ㄹ",
    content = "크크",
    sendTime = "오후 1:24",
    from = From.Other(
        nickname = "곽하민",
        profileUrl = "https://static01.nyt.com/images/2014/08/24/arts/24GRANDE1/24JPGRANDE1-superJumbo.jpg",
    )
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
        item {
            Message(otherReplyForTextMessageDummyData)
        }
        item {
            Message(myReplyForTextMessageDummyData)
        }
    }
}

@Composable
fun Message(
    message: Message
){
    Row(
        horizontalArrangement = if(message.from is From.My) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        if(message.from is From.Other) {
            ImageProfile(profileUrl = (message.from as From.Other).profileUrl)
            Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))
        }

        AuthorAndMessage(message = message)
    }
}

@Composable
fun AuthorAndMessage(
    message: Message
){
    Column {
        if(message.from is From.Other) {
            Text(
                text = (message.from as From.Other).nickname,
                style = MaterialTheme.typography.bodyMedium.copy(color = LocalContentColor.current),
            )

            Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))
        }

        MessageAndSendTime(message = message)
    }
}

private val OtherChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
private val MyChatBubbleShape = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)

@Composable
fun MessageAndSendTime(
    message: Message,
){
    val backgroundColor = if(message.from == From.My) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    val shape = if(message.from == From.My) {
        MyChatBubbleShape
    } else {
        OtherChatBubbleShape
    }

    Row(verticalAlignment = Alignment.Bottom) {
        if(message.from == From.My){
            Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))

            Text(
                text = message.sendTime,
                style = MaterialTheme.typography.bodySmall.copy(color = LocalContentColor.current),
            )
        }

        Surface(
            modifier = Modifier.widthIn(max = ChatComponentStyle.maxWidth),
            color = backgroundColor,
            shape = shape
        ) {
            when(message){
                is Text -> TextMessageBubble(message = message)
                is ReplyText -> ReplyForTextMessageBubble(message = message)
            }
        }

        if(message.from is From.Other){
            Spacer(modifier = Modifier.padding(ChatComponentStyle.smallSpacing))

            Text(
                text = message.sendTime,
                style = MaterialTheme.typography.bodySmall.copy(color = LocalContentColor.current),
            )
        }
    }
}

@Composable
fun TextMessageBubble(message: Text) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = message.content,
        style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
    )
}

@Composable
fun ReplyForTextMessageBubble(message: ReplyText){
    Column {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Text(
                text = "${message.replyNickname}에게 답장",
                style = MaterialTheme.typography.titleMedium.copy(color = LocalContentColor.current),
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = message.replyContent,
                style = MaterialTheme.typography.bodyMedium.copy(color = LocalContentColor.current),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Divider(
            thickness = 0.5.dp,
            color = Color.Gray
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text = message.content,
            style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
        )
    }
}

private object ChatComponentStyle {
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
fun MyReplyForTextMessagePreview(){
    HohoiTheme {
        Message(myReplyForTextMessageDummyData)
    }
}

@Preview(showBackground = true)
@Composable
fun OtherReplyForTextMessagePreview(){
    HohoiTheme {
        Message(otherReplyForTextMessageDummyData)
    }
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    HohoiTheme {
        ChatDetailScreen(chatDetailId = "1")
    }
}