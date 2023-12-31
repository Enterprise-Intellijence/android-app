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

import io.swagger.client.models.CustomMoney
import io.swagger.client.models.Message
import io.swagger.client.models.Order
import io.swagger.client.models.Product
import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param amount 
 * @param creationTime 
 * @param state 
 * @param offerer 
 * @param product 
 * @param message 
 * @param order 
 */
data class Offer (

    val id: kotlin.String? = null,
    val amount: CustomMoney? = null,
    val creationTime: java.time.LocalDateTime? = null,
    val state: State? = null,
    val offerer: User? = null,
    val product: Product? = null,
    val message: Message? = null,
    val order: Order? = null
) {
    /**
    * 
    * Values: PENDING,ACCEPTED,REJECTED,CANCELLED
    */
    enum class State(val value: kotlin.String){
        PENDING("PENDING"),
        ACCEPTED("ACCEPTED"),
        REJECTED("REJECTED"),
        CANCELLED("CANCELLED");
    }
}