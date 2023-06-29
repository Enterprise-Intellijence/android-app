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

import io.swagger.client.models.Transaction
import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param creditCard 
 * @param expiryDate 
 * @param owner 
 * @param ownerUser 
 * @param transaction 
 * @param default 
 */
data class PaymentMethod (

    val id: kotlin.String? = null,
    val creditCard: kotlin.String? = null,
    val expiryDate: java.time.LocalDate? = null,
    val owner: kotlin.String? = null,
    val ownerUser: User? = null,
    val transaction: kotlin.Array<Transaction>? = null,
    val default: kotlin.Boolean? = null
) {
}