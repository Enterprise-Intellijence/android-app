package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.view_models.MessagePageViewModel
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserImageDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MessagesPage() {
    val messagePageViewModel: MessagePageViewModel = viewModel()

    Column() {
        Text(text = "Messages Page")
        Spacer(modifier = Modifier.size(8.dp))

        if (messagePageViewModel.conversationList.isEmpty()) {
            messagePageViewModel.loadConversations()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        } else {
            ConversationList(messagePageViewModel.conversationList)
        }
    }


}

@Composable
private fun ConversationList(
    conversationList: List<ConversationDTO>
) {
    LazyColumn {
        items(conversationList) { conversation ->
            key(conversationList.map { it.conversationId }) {

                ConversationCard(conversation)
                Spacer(Modifier.size(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun ConversationCardPreview() {
    val conversation = ConversationDTO(
        conversationId = "1",
        otherUser = UserBasicDTO(
            id = "1",
            username = "Mario Rossi",
        ),
        lastMessage = MessageDTO(
            id = "1",
            sendUser = UserBasicDTO(
                id = "1",
                username = "Mario Rossi",
                photoProfile = UserImageDTO(
                    id = "1",
                    urlPhoto = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png"
                )
            ),
            text = "Ciao come stai?Ciao come stai?Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"Ciao come stai?\"",
            messageDate = LocalDateTime.now().minusHours(1),
            conversationId = "1",
            messageStatus = MessageDTO.MessageStatus.UNREAD,
            receivedUser = UserBasicDTO(
                id = "2",
                username = "Luigi Verdi",
            ),

            ),
        unreadMessages = true,
    )
    ConversationCard(conversation)
}

fun timeToString(messageDate: LocalDateTime): String {
    return if (messageDate.toLocalDate() == LocalDateTime.now().toLocalDate()) {
        messageDate.format(DateTimeFormatter.ofPattern("HH:mm"))
    } else {
        messageDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ConversationCard(conversation: ConversationDTO) {
    val isLastMessageFromOther = conversation.lastMessage.sendUser.id == conversation.otherUser.id

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 6.dp)
        ) {
            Box(modifier = Modifier.size(50.dp)) {
                if (conversation.productBasicDTO != null) {
                    Image(painter = rememberImagePainter(
                        data = conversation.productBasicDTO.productImages?.get(0)?.urlPhoto,
                        builder = {
                            transformations(RoundedCornersTransformation(/*radius*/ 8f))
                        }
                    ),
                        contentDescription = "product image",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {

                Box(
                    contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()
                ) {
                    UserPictureAndName(
                        user = conversation.otherUser,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                    Text(
                        text = timeToString(conversation.lastMessage.messageDate!!),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                }

                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = conversation.lastMessage.text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    val messageStateText: String = if (isLastMessageFromOther) {
                        if (conversation.lastMessage.messageStatus == MessageDTO.MessageStatus.UNREAD)
                            "NEW" else ""
                    } else {
                        if (conversation.lastMessage.messageStatus == MessageDTO.MessageStatus.UNREAD)
                            "✓" else "✓✓"
                    }

                    if (messageStateText.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = messageStateText,
                                modifier = Modifier
                                    .background(
                                        color = Primary, shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(4.dp)

                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserPictureAndName(user: UserBasicDTO, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        Image(painter = rememberImagePainter(
            data = user.photoProfile?.urlPhoto,
            builder = {
                transformations(RoundedCornersTransformation(/*radius*/ 8f))
            }
        ),
            contentDescription = "avatar",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = user.username,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
