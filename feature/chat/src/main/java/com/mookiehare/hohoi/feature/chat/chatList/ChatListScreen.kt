package com.mookiehare.hohoi.feature.chat.chatList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mookiehare.hohoi.core.design.icon.HelloiIcons
import com.mookiehare.hohoi.core.design.theme.HohoiTheme
import com.mookiehare.hohoi.feature.chat.model.ChatRoom

val chatRoomDummyData = listOf(
    ChatRoom(
        id = "1",
        nickname = "곽하민",
        content = "[PLAY 꼼데가르송 RESTOCK]\n\n꾸준히 사랑받는 PLAY 꼼데가르송",
        receivedTime = "오후 5:01",
        profile = "https://static01.nyt.com/images/2014/08/24/arts/24GRANDE1/24JPGRANDE1-superJumbo.jpg",
    ),
    ChatRoom(
        id = "2",
        nickname = "김태양",
        content = "송금이 완료되었어요.\n\n- 일시 : 2023. 1. 15.(일) 17:11",
        receivedTime = "오후 5:11",
        profile = "https://img.danawa.com/prod_img/500000/147/615/img/14615147_1.jpg?shrink=330:330&_v=20220426173016",
    ),
    ChatRoom(
        id = "3",
        nickname = "강철",
        content = "(광고)신년맞이 음식 운세",
        receivedTime = "오후 5:21",
        profile = null,
    ),
    ChatRoom(
        id = "4",
        nickname = "이현묵",
        content = "(광고)아직 1+1 이벤트 참여 안했나요?",
        receivedTime = "오후 5:31",
        profile = "https://img.hankyung.com/photo/202209/03.31140810.1.jpg",
    ),
    ChatRoom(
        id = "5",
        nickname = "신현화",
        content = "(광고)스트레스 받을 땐?",
        receivedTime = "오후 5:41",
        profile = null,
    ),
)

@Composable
internal fun ChatListRoute(
    modifier: Modifier = Modifier,
    viewModel: ChatListViewModel = hiltViewModel(),
    navigateToChatDetail: (String) -> Unit
) {

    ChatListScreen(
        modifier = modifier,
        chatRoomList = chatRoomDummyData,
        navigateToChatDetail
    )
}

@Composable
internal fun ChatListScreen(
    modifier: Modifier = Modifier,
    chatRoomList: List<ChatRoom>,
    navigateToChatDetail: (String) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
        items(chatRoomList){ chatRoom ->
            ChatListItem(
                chatRoom = chatRoom,
                navigateToChatDetail = navigateToChatDetail
            )
        }
    }
}

@Composable
internal fun ChatListItem(
    modifier: Modifier = Modifier,
    chatRoom: ChatRoom,
    navigateToChatDetail: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { navigateToChatDetail(chatRoom.id) }
            .fillMaxWidth()
            .padding(4.dp)
            .heightIn(max = 60.dp)
    ){
        val profileImageModifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(40))
        if(chatRoom.profile == null){
            Icon(
                modifier = profileImageModifier
                    .background(Color.Red),
                imageVector = HelloiIcons.Person,
                contentDescription = null // decorative image
            )
        }else{
            AsyncImage(
                model = chatRoom.profile,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = profileImageModifier
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = chatRoom.nickname)
            Text(
                text = chatRoom.content,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = chatRoom.receivedTime,
            fontSize = 12.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListItemPreview() {
    HohoiTheme {
        ChatListItem(
            chatRoom = chatRoomDummyData[2],
            navigateToChatDetail = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    HohoiTheme {
        ChatListScreen(
            chatRoomList = chatRoomDummyData,
            navigateToChatDetail = {}
        )
    }
}