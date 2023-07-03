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
import io.swagger.client.models.NotificationDTO
import io.swagger.client.models.PageNotificationDTO

import io.swagger.client.infrastructure.*

class NotificationControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param page  (optional, default to 0)
     * @param sizePage  (optional, default to 10)
     * @return PageNotificationDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getAllUserNotifications(page: kotlin.Int? = null, sizePage: kotlin.Int? = null): PageNotificationDTO {
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
                "/api/v1/notifications/", query = localVariableQuery
        )
        val response = request<PageNotificationDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageNotificationDTO
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
     * @return NotificationDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun readNotification(body: NotificationDTO): NotificationDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/notifications/"
        )
        val response = request<NotificationDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as NotificationDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
