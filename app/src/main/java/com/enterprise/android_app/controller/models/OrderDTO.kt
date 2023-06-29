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

import io.swagger.client.models.AddressDTO
import io.swagger.client.models.DeliveryDTO
import io.swagger.client.models.OfferBasicDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.TransactionDTO
import io.swagger.client.models.UserBasicDTO

/**
 * 
 * @param id 
 * @param state 
 * @param orderDate 
 * @param orderUpdateDate 
 * @param product 
 * @param user 
 * @param delivery 
 * @param deliveryAddress 
 * @param offer 
 * @param transaction 
 */
data class OrderDTO (

    val id: kotlin.String,
    val state: State,
    val orderDate: java.time.LocalDateTime? = null,
    val orderUpdateDate: java.time.LocalDateTime? = null,
    val product: ProductBasicDTO? = null,
    val user: UserBasicDTO? = null,
    val delivery: DeliveryDTO? = null,
    val deliveryAddress: AddressDTO? = null,
    val offer: OfferBasicDTO? = null,
    val transaction: TransactionDTO? = null
) {
    /**
    * 
    * Values: CANCELED,PENDING,PURCHASED,SHIPPED,DELIVERED,COMPLETED,REVIEWED
    */
    enum class State(val value: kotlin.String){
        CANCELED("CANCELED"),
        PENDING("PENDING"),
        PURCHASED("PURCHASED"),
        SHIPPED("SHIPPED"),
        DELIVERED("DELIVERED"),
        COMPLETED("COMPLETED"),
        REVIEWED("REVIEWED");
    }
}