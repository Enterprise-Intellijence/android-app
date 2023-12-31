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
package io.swagger.client.models

import io.swagger.client.models.Offer
import io.swagger.client.models.Product
import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param text 
 * @param conversationId 
 * @param messageDate 
 * @param messageStatus 
 * @param product 
 * @param sendUser 
 * @param receivedUser 
 * @param offer 
 */
data class Message (

    val id: kotlin.String? = null,
    val text: kotlin.String? = null,
    val conversationId: kotlin.String? = null,
    val messageDate: java.time.LocalDateTime? = null,
    val messageStatus: MessageStatus? = null,
    val product: Product? = null,
    val sendUser: User? = null,
    val receivedUser: User? = null,
    val offer: Offer? = null
) {
    /**
    * 
    * Values: READ,UNREAD
    */
    enum class MessageStatus(val value: kotlin.String){
        READ("READ"),
        UNREAD("UNREAD");
    }
}