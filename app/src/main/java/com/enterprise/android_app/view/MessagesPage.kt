package com.enterprise.android_app.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.model.CurrentDataUtils.chatUser
import com.enterprise.android_app.model.CurrentDataUtils.inChat
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.view.components.TopBarGeneric
import com.enterprise.android_app.view.components.TopBarSearch
import com.enterprise.android_app.view_models.MessagePageViewModel
import com.enterprise.android_app.view_models.OfferViewModel
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserImageDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesPage(navController: NavHostController) {
    val messagePageViewModel: MessagePageViewModel = viewModel()
    val offerViewModel: OfferViewModel = viewModel()

    LaunchedEffect(CurrentDataUtils.chatUserId) {
        if (CurrentDataUtils.chatUserId.value != null) {
            messagePageViewModel.openChat(
                CurrentDataUtils.chatUserId.value!!,
                CurrentDataUtils.chatProductId.value
            )
            CurrentDataUtils.chatUserId.value = null
            CurrentDataUtils.chatProductId.value = null

            messagePageViewModel.isMakingOffer.value = CurrentDataUtils.makeOffer.value
            CurrentDataUtils.makeOffer.value = false
        }
    }


    Column {

        if (!inChat.value) {
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
                ConversationList(
                    messagePageViewModel.conversationList,
                    messagePageViewModel::openChat
                )
            }
        } else if (inChat.value && chatUser.value != null) {

            ChatPage(
                chatUser.value!!,
                messagePageViewModel.chatProduct.value,
                messagePageViewModel.chatMessages,
                isMakingOffer = messagePageViewModel.isMakingOffer.value,
                onSendMessage = { messagePageViewModel.sendMessage(it) },
                onBack = (messagePageViewModel::clearCurrentConversation),
                onMakeOffer = { text: String, product: ProductBasicDTO ->
                    makeOffer(text, product, offerViewModel)
                },
                offerToggle = (messagePageViewModel::toggleMakeOffer),
                navController = navController
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }
        }


    }
}

fun makeOffer(text: String, product: ProductBasicDTO, offerViewModel: OfferViewModel) {
    val amount = text.toDoubleOrNull()

    if (amount != null) {

        val money = CustomMoneyDTO(
            price = amount,
            currency = product.productCost.currency
        )
        offerViewModel.makeOffer(
            product,
            money
        )
    } else {
        Log.e("MessagesPage", "MessagesPage: $text is not a valid number")
    }
}


@Composable
private fun ConversationList(
    conversationList: List<ConversationDTO>,
    onClick: (ConversationDTO) -> Unit = {}
) {
    LazyColumn {
        items(conversationList) { conversation ->
            ConversationCard(conversation, Modifier, onClick)
            Spacer(Modifier.size(8.dp))

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
fun ConversationCard(
    conversation: ConversationDTO,
    modifier: Modifier = Modifier,
    onClick: (ConversationDTO) -> Unit = {}
) {
    val isLastMessageFromOther = conversation.lastMessage.sendUser.id == conversation.otherUser.id

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(conversation) }
    )
    {
        Row(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.size(50.dp)) {
                if (conversation.productBasicDTO != null) {
                    Image(painter = /*radius*/rememberAsyncImagePainter(
                        ImageRequest.Builder(
                            LocalContext.current
                        ).data(data = conversation.productBasicDTO.productImages?.get(0)?.urlPhoto)
                            .apply(block = fun ImageRequest.Builder.() {
                                transformations(RoundedCornersTransformation(/*radius*/ 8f))
                            }).build()
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
                        messageStatusToString(conversation.lastMessage.messageStatus!!)
                    }

                    if (messageStateText.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = messageStateText,
                                modifier = Modifier
                                    .background(
                                        color = Primary, shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(4.dp),
                                fontSize = 10.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun messageStatusToString(messageStatus: MessageDTO.MessageStatus): String {
    return if (messageStatus == MessageDTO.MessageStatus.UNREAD)
        "✓" else "✓✓"
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserPictureAndName(user: UserBasicDTO, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        Image(painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = user.photoProfile?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                    transformations(RoundedCornersTransformation(4f))
                }).build()
        ),
            contentDescription = stringResource(id = R.string.profile_picture),
            modifier = Modifier
                .size(30.dp)
                .aspectRatio(1f)
                .clip(CircleShape))
        Text(
            text = user.username,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
