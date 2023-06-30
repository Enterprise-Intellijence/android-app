/**
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.apis

import com.enterprise.android_app.controller.BasePath
import io.swagger.client.models.ConversationDTO
import io.swagger.client.models.MessageCreateDTO
import io.swagger.client.models.MessageDTO
import io.swagger.client.models.PageMessageDTO

import io.swagger.client.infrastructure.*

class MessageControllerApi(basePath: kotlin.String = com.enterprise.android_app.controller.BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param body  
     * @return MessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun createMessage(body: MessageCreateDTO): MessageDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/messages"
        )
        val response = request<MessageDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun deleteMessage(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/messages/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return kotlin.Array<ConversationDTO>
     */
    @Suppress("UNCHECKED_CAST")
    fun getAllMyConversations(): kotlin.Array<ConversationDTO> {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/messages/conversations"
        )
        val response = request<kotlin.Array<ConversationDTO>>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Array<ConversationDTO>
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param conversationId  
     * @param page  (optional, default to 0)
     * @param sizePage  (optional, default to 10)
     * @return PageMessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getConversation(conversationId: kotlin.String, page: kotlin.Int? = null, sizePage: kotlin.Int? = null): PageMessageDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (page != null) {
                put("page", listOf(page.toString()))
            }
            if (sizePage != null) {
                put("sizePage", listOf(sizePage.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/messages/conversations/{conversationId}".replace("{" + "conversationId" + "}", "$conversationId"), query = localVariableQuery
        )
        val response = request<PageMessageDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageMessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return MessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getMessage(id: kotlin.String): MessageDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/messages/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<MessageDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param id  
     * @return MessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun replaceMessage(body: MessageDTO, id: kotlin.String): MessageDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/messages/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<MessageDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return void
     */
    fun setReadMessages(body: kotlin.Array<kotlin.String>): Unit {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/messages/read"
        )
        val response = request<Any?>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param id  
     * @return MessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun updateMessage(body: MessageDTO, id: kotlin.String): MessageDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/api/v1/messages/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<MessageDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as MessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
