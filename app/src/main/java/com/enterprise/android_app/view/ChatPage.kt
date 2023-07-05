package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.ui.theme.Purple80
import com.enterprise.android_app.ui.theme.PurpleGrey80
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserImageDTO
import java.time.LocalDateTime

@Composable
fun ChatPage(
    otherUser: UserBasicDTO,
    productBasicDTO: ProductBasicDTO?,
    messages: SnapshotStateList<MessageDTO>,
    onSendMessage: (String) -> Unit = {}
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ChatHeader(otherUser, productBasicDTO)
        ChatMessageList(messages)
        ChatInput(onSendMessage = onSendMessage)
    }
}


@Composable
fun ChatHeader(
    otherUser: UserBasicDTO,
    productBasicDTO: ProductBasicDTO?
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            UserPictureAndName(user = otherUser)
        }
        if (productBasicDTO != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProductPictureAndTitle(productBasicDTO = productBasicDTO)
                ProductPrice(price = productBasicDTO.productCost)
            }
        }
    }
}

@Composable
fun ProductPrice(price: CustomMoneyDTO, modifier: Modifier = Modifier) {
    val currencySymbol = when (price.currency) {
        CustomMoneyDTO.Currency.EUR -> "$"
        CustomMoneyDTO.Currency.USD -> "€"
        else -> "?"
    }

    val priceText = currencySymbol + price.price.toString()

    Text(
        text = priceText,
        modifier = modifier
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductPictureAndTitle(
    productBasicDTO: ProductBasicDTO,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        Image(painter = rememberImagePainter(
            data = productBasicDTO.productImages?.get(0)?.urlPhoto,
            builder = {
                transformations(RoundedCornersTransformation(/*radius*/ 8f))
            }
        ),
            contentDescription = "product image",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = productBasicDTO.title!!,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }

}

@Composable
fun ChatMessageList(
    messages: SnapshotStateList<MessageDTO>
) {
    LazyColumn(content = {
        items(messages, key = { message -> message.id!! }) { message ->
            ChatMessage(
                message = message,
                isMyMessage = message.sendUser.id == CurrentDataUtils.currentUser?.id
            )
        }
    }, modifier = Modifier.fillMaxSize())
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun ChatMessage(
    message: MessageDTO,
    isMyMessage: Boolean
) {
    Row(
        modifier = Modifier.run {
            fillMaxWidth()
                .padding(8.dp)
                .padding(
                    start = if (isMyMessage) 50.dp else 0.dp,
                    end = if (isMyMessage) 0.dp else 50.dp,
                )
        },
        horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isMyMessage) PurpleGrey80 else Purple80
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = timeToString(message.messageDate!!),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                    if (isMyMessage) {
                        Text(
                            text = messageStatusToString(message.messageStatus!!),
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun ChatMessagePreview() {
    val user1 = UserBasicDTO(
        id = "1",
        username = "John Doe",
        photoProfile = UserImageDTO(
            id = "1",
            urlPhoto = "https://www.w3schools.com/howto/img_avatar.png"
        ),
    )
    val user2 = UserBasicDTO(
        id = "2",
        username = "Jane Smith",
        photoProfile = UserImageDTO(
            id = "2",
            urlPhoto = "https://www.w3schools.com/howto/img_avatar2.png"
        ),
    )


    Column() {


        ChatMessage(
            message = MessageDTO(
                id = "1",
                text = "Hello how are you?",
                sendUser = user1,
                messageDate = LocalDateTime.now().minusHours(2),
                messageStatus = MessageDTO.MessageStatus.READ,
                conversationId = "1",
                receivedUser = user2
            ),
            true
        )
        ChatMessage(
            message = MessageDTO(
                id = "1",
                text = "Hello, My name is Jane, my cat is sick, can you help me?\npretty please?",
                sendUser = user2,
                messageDate = LocalDateTime.now().minusHours(1),
                messageStatus = MessageDTO.MessageStatus.READ,
                conversationId = "1",
                receivedUser = user1
            ),
            false
        )
    }
}


@Composable
fun ChatInput(
    onSendMessage: (String) -> Unit = {}
) {

}


