package com.enterprise.android_app.view

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.view_models.HomePageViewModel
import com.enterprise.android_app.view_models.MessagePageViewModel
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserImageDTO
import java.time.LocalDateTime

@Composable
fun MessagesPage() {
    val messagePageViewModel = remember { MessagePageViewModel() }

    LazyColumn {
        items(messagePageViewModel.conversationList) { conversation ->
            key(messagePageViewModel.conversationList.map { it.conversationId }) {

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
            text = "Ciao come stai?",
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ConversationCard(conversation: ConversationDTO) {
    Card {
        Row (Modifier.padding(all = 6.dp)) {
            Image(painter = rememberImagePainter(
                data = conversation.otherUser.photoProfile?.urlPhoto,
                builder = {
                    transformations(RoundedCornersTransformation(/*radius*/ 8f))
                }
            ),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(text = conversation.otherUser.username)
        }
    }
}
