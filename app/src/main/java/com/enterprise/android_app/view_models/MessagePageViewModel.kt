package com.enterprise.android_app.view_models

import androidx.lifecycle.ViewModel
import io.swagger.client.apis.MessageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.MessageCreateDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.ProductBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagePageViewModel : ViewModel() {

    private val messageControllerApi: MessageControllerApi = MessageControllerApi()
    private val userControllerApi: UserControllerApi = UserControllerApi()
    private val productControllerApi: ProductControllerApi = ProductControllerApi()


    var conversationList = mutableListOf<ConversationDTO>()
    var messages = mutableListOf<MessageDTO>()
    var currentConversation: ConversationDTO? = null


    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    fun loadConversations() {
        coroutineScope.launch {
            try {
                val updatedConversations = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.getAllMyConversations().toList()
                }

                conversationList = updatedConversations as MutableList<ConversationDTO>
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadMessagesForConversation(conversationId: String) {
        coroutineScope.launch {
            try {
                val pageResponse = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.getConversation(conversationId, 0, 1000)
                }
                messages = pageResponse.content!!.toMutableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendMessage(message: String, conversation: ConversationDTO) {
        coroutineScope.launch {
            try {
                val newMessage = MessageCreateDTO(
                    conversationId = conversation.conversationId,
                    text = message,
                    product = conversation.productBasicDTO,
                    receivedUser = conversation.otherUser
                )

                val sentMessage = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.createMessage(newMessage)
                }

                // add message at the start of the list
                messages.add(0, sentMessage)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendMessage(message: String, otherUserID: String, productID: String?) {
        val conversation = findConversationWith(otherUserID, productID)
        if (conversation != null) {
            sendMessage(message, conversation)
            return
        }

        coroutineScope.launch {
            try {
                val receivedUser = withContext(coroutineScope.coroutineContext) {
                    userControllerApi.userById(otherUserID)
                }

                val product: ProductBasicDTO? = if (productID == null) null else
                    withContext(coroutineScope.coroutineContext) {
                        productControllerApi.productById(productID) as ProductBasicDTO // TODO: Remove cast
                    }


                val newMessage = MessageCreateDTO(
                    conversationId = null,
                    text = message,
                    product = product,
                    receivedUser = receivedUser
                )

                val sentMessage = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.createMessage(newMessage)
                }

                // add message at the start of the list
                messages = mutableListOf(sentMessage)
                loadConversations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    fun findConversationWith(otherUserID: String, productID: String?): ConversationDTO? {
        for (conversation in conversationList) {
            if (conversation.otherUser.id == otherUserID) {
                val conversationProductID = conversation.productBasicDTO?.id

                if (productID == conversationProductID) {
                    return conversation
                }
            }
        }
        return null
    }

    fun existsConversationWith(otherUserID: String, productID: String?): Boolean {
        return findConversationWith(otherUserID, productID) != null
    }
}

