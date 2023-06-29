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

import io.swagger.client.models.OfferBasicDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserBasicDTO

/**
 * 
 * @param id 
 * @param conversationId 
 * @param text 
 * @param messageDate 
 * @param messageStatus 
 * @param product 
 * @param sendUser 
 * @param receivedUser 
 * @param offer 
 */
data class MessageDTO (

    val id: kotlin.String? = null,
    val conversationId: kotlin.String? = null,
    val text: kotlin.String,
    val messageDate: java.time.LocalDateTime? = null,
    val messageStatus: MessageStatus? = null,
    val product: ProductBasicDTO? = null,
    val sendUser: UserBasicDTO,
    val receivedUser: UserBasicDTO,
    val offer: OfferBasicDTO? = null
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