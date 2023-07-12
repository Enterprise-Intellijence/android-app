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

import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.PaymentMethodBasicDTO

/**
 * 
 * @param id 
 * @param creationTime 
 * @param amount 
 * @param transactionState 
 * @param paymentMethod 
 * @param order 
 */
data class TransactionDTO (

    val id: kotlin.String,
    val creationTime: java.time.LocalDateTime? = null,
    val amount: CustomMoneyDTO,
    val transactionState: TransactionState,
    val paymentMethod: kotlin.String,
    val paymentMethodOwner: kotlin.String,
) {
    /**
    * 
    * Values: REJECTED,COMPLETED
    */
    enum class TransactionState(val value: kotlin.String){
        REJECTED("REJECTED"),
        COMPLETED("COMPLETED");
    }
}