package com.enterprise.android_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.Primary
import com.enterprise.android_app.ui.theme.Purple80
import com.enterprise.android_app.ui.theme.PurpleGrey80
import com.enterprise.android_app.view_models.MessagePageViewModel
import com.enterprise.android_app.view_models.OfferViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.OfferBasicDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ProductCategoryDTO
import io.swagger.client.models.UserBasicDTO
import io.swagger.client.models.UserImageDTO
import java.time.LocalDateTime

@Composable
fun ChatPage(
    otherUser: UserBasicDTO,
    productBasicDTO: ProductBasicDTO?,
    messages: SnapshotStateList<MessageDTO>,
    isMakingOffer: Boolean,
    onSendMessage: (String) -> Unit = {},
    onBack: () -> Unit = {},
    onMakeOffer: (String, ProductBasicDTO) -> Unit = { _, _ -> },
    offerToggle: () -> Unit = {},
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ChatMessageList(messages, modifier = Modifier.weight(1f))
        ChatInput(
            isMakingOffer,
            onSendMessage = {
                if (isMakingOffer && productBasicDTO != null)
                    onMakeOffer(it, productBasicDTO)
                else onSendMessage(it)
            }
        )
    }
}


@Composable
fun ChatHeader(
    otherUser: UserBasicDTO,
    productBasicDTO: ProductBasicDTO?,
    isMakingOffer: Boolean,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    offerToggle: () -> Unit = {},
    navController: NavHostController
) {
    val messagePageViewModel: MessagePageViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.1f),
                horizontalAlignment = Alignment.Start
            ){
                IconButton(
                    onClick = { messagePageViewModel.clearCurrentConversation() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.height(20.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                UserPictureAndName(user = otherUser)
            }


        }
    }
    if (productBasicDTO != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProductPictureAndTitle(
                productBasicDTO = productBasicDTO,
                modifier = Modifier.weight(1f)
            )
            ProductPrice(price = productBasicDTO.productCost)

            if (productBasicDTO.seller?.id!! == otherUser.id) {

                Button(onClick = offerToggle) {
                    Text(text = if (isMakingOffer) "Cancel" else "Make Offer")
                }
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

        Image(painter = /*radius*/rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = productBasicDTO.productImages?.get(0)?.urlPhoto).apply(block = fun ImageRequest.Builder.() {
                    transformations(RoundedCornersTransformation(/*radius*/ 8f))
                }).build()
        ),
            contentDescription = "product image",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = productBasicDTO.title,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }

}

@Composable
fun ChatMessageList(
    messages: SnapshotStateList<MessageDTO>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        content = {
            items(messages, key = { message -> message.id!! }) { message ->
                ChatMessage(
                    message = message,
                    isMyMessage = message.sendUser.id == CurrentDataUtils.currentUser?.id
                )
            }
        }, modifier = modifier.fillMaxWidth(),
        reverseLayout = true
    )
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
                        color = if (isMyMessage) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.inversePrimary
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.offer != null) {
                    OfferMessage(message, isMyMessage)
                }

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

@Composable
fun OfferMessage(message: MessageDTO, isMyMessage: Boolean) {

    if (message.offer == null) {
        return
    }

    val offerViewModel = OfferViewModel()


    Column(modifier = Modifier.fillMaxWidth()) {
        Row() {
            val offerPriceText =
                "${message.offer.amount?.price} ${message.offer.amount?.currency}"
            val productPriceText =
                "${message.product?.productCost?.price} ${message.product?.productCost?.currency}"
            val text = "$offerPriceText instead of $productPriceText"
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .background(Primary, RoundedCornerShape(8.dp))
                    .padding(8.dp),

                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "status: ${message.offer.state.toString()}",
                    style = MaterialTheme.typography.bodyMedium,
                )

            }
        }
        if (message.offer.state == OfferBasicDTO.State.PENDING) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isMyMessage) {
                    Button(
                        onClick = { offerViewModel.cancelOffer(message) },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                } else {
                    Button(
                        onClick = { offerViewModel.acceptOffer(message) },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Accept")
                    }
                    Button(
                        onClick = { offerViewModel.declineOffer(message) },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "Decline")
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun ChatInputPrev() {
    ChatInput(true)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput(
    isMakingOffer: Boolean,
    onSendMessage: (String) -> Unit = {}
) {
    var message by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(8.dp)

    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically),
            placeholder = {
                Text(text = "Type a message")
            },
            keyboardOptions = if (isMakingOffer) {
                KeyboardOptions(keyboardType = KeyboardType.Number)
            } else {
                KeyboardOptions.Default
            }

        )
        Button(
            onClick = {
                onSendMessage(message)
                message = ""
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            val text = if (isMakingOffer) {
                "Make Offer"
            } else {
                "Send"
            }
            Text(text = text)
        }
    }

}


