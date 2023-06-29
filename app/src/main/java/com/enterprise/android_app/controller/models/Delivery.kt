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

import io.swagger.client.models.Address
import io.swagger.client.models.CustomMoney
import io.swagger.client.models.Order

/**
 * 
 * @param id 
 * @param order 
 * @param sendTime 
 * @param deliveredTime 
 * @param deliveryCost 
 * @param shipper 
 * @param deliveryStatus 
 * @param senderAddress 
 * @param receiverAddress 
 */
data class Delivery (

    val id: kotlin.String? = null,
    val order: Order? = null,
    val sendTime: java.time.LocalDateTime? = null,
    val deliveredTime: java.time.LocalDateTime? = null,
    val deliveryCost: CustomMoney? = null,
    val shipper: kotlin.String? = null,
    val deliveryStatus: DeliveryStatus? = null,
    val senderAddress: Address? = null,
    val receiverAddress: Address? = null
) {
    /**
    * 
    * Values: SHIPPED,DELIVERED
    */
    enum class DeliveryStatus(val value: kotlin.String){
        SHIPPED("SHIPPED"),
        DELIVERED("DELIVERED");
    }
}