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

import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param receiver 
 * @param userTarget 
 * @param productTarget 
 * @param offerTarget 
 * @param reviewTarget 
 * @param text 
 * @param read 
 * @param date 
 * @param type 
 */
data class Notification (

    val id: kotlin.String? = null,
    val receiver: User? = null,
    val userTarget: kotlin.String? = null,
    val productTarget: kotlin.String? = null,
    val offerTarget: kotlin.String? = null,
    val reviewTarget: kotlin.String? = null,
    val text: kotlin.String? = null,
    val read: kotlin.Boolean? = null,
    val date: java.time.LocalDateTime? = null,
    val type: Type? = null
) {
    /**
    * 
    * Values: NEWOFFER,OFFERREJECTED,OFFERACCEPTED,REVIEW,MESSAGE,SALE,PURCHASE,PRODUCTSOLD,NEWPRODUCT,FOLLOW,USER,NEWFAVORITE
    */
    enum class Type(val value: kotlin.String){
        NEWOFFER("NEW_OFFER"),
        OFFERREJECTED("OFFER_REJECTED"),
        OFFERACCEPTED("OFFER_ACCEPTED"),
        REVIEW("REVIEW"),
        MESSAGE("MESSAGE"),
        SALE("SALE"),
        PURCHASE("PURCHASE"),
        PRODUCTSOLD("PRODUCT_SOLD"),
        NEWPRODUCT("NEW_PRODUCT"),
        FOLLOW("FOLLOW"),
        USER("USER"),
        NEWFAVORITE("NEW_FAVORITE");
    }
}