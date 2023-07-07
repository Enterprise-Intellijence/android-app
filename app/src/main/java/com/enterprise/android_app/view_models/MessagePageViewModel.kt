package com.enterprise.android_app.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.MessageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.apis.UserControllerApi
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.MessageCreateDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserBasicDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagePageViewModel : ViewModel() {

    private val TAG = "MessagePageViewModel"

    private val messageControllerApi: MessageControllerApi = MessageControllerApi()
    private val userControllerApi: UserControllerApi = UserControllerApi()
    private val productControllerApi: ProductControllerApi = ProductControllerApi()


    var conversationList = mutableStateListOf<ConversationDTO>()
    var chatMessages = mutableStateListOf<MessageDTO>()

    var chatConversation = mutableStateOf<ConversationDTO?>(null)
    val chatUser = mutableStateOf(null as UserBasicDTO?)
    var chatProduct = mutableStateOf(null as ProductBasicDTO?)

    val inChat = mutableStateOf(false)
    val isMakingOffer = mutableStateOf(false)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    fun loadConversations() {
        coroutineScope.launch {
            try {
                val updatedConversations = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.getAllMyConversations().toList()
                }

                conversationList.clear()
                conversationList.addAll(updatedConversations)
                Log.d(TAG, "loadConversations: ${conversationList.size}")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadMessagesForConversation(conversationId: String) {
        coroutineScope.launch {
            try {
                val pageResponse = withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.getConversationMessages(conversationId, 0, 1000)
                }

                chatMessages.clear()
                chatMessages.addAll(pageResponse.content!!)
                readMessages()

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
                chatMessages.add(0, sentMessage)

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
                        productControllerApi.productBasicById(productID)
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
                chatMessages.add(0, sentMessage)
                loadConversations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendMessage(message: String) {
        if (chatConversation.value == null) return
        else sendMessage(message, chatConversation.value!!)
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


    fun readMessages() {
        coroutineScope.launch {
            try {
                val unreadMessagesIds = chatMessages.filter {
                    it.sendUser.id != CurrentDataUtils.currentUser?.id && it.messageStatus == MessageDTO.MessageStatus.UNREAD
                }.map { it.id!! }.toTypedArray()

                withContext(coroutineScope.coroutineContext) {
                    messageControllerApi.setReadMessages(unreadMessagesIds)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun openConversation(conversationDTO: ConversationDTO?) {
        chatConversation.value = conversationDTO
        if (conversationDTO != null) {
            loadMessagesForConversation(conversationDTO.conversationId!!)
        }


        inChat.value = true
        chatUser.value = conversationDTO?.otherUser
        chatProduct.value = conversationDTO?.productBasicDTO
    }


    fun clearCurrentConversation() {
        chatConversation.value = null
        chatUser.value = null
        chatProduct.value = null

        inChat.value = false
        isMakingOffer.value = false
        chatMessages.clear()
    }


    fun toggleMakeOffer() {
        isMakingOffer.value = !isMakingOffer.value
    }

    fun refreshMessage(id: String) {
        coroutineScope.launch {
            try {

                val message = messageControllerApi.getMessage(id)

                val index = chatMessages.indexOfFirst { it.id == id }
                if (index != -1) {
                    chatMessages[index] = message
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

