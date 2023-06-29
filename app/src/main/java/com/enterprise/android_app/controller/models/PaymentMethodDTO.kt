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


/**
 * 
 * @param id 
 * @param creditCard 
 * @param expiryDate 
 * @param owner 
 * @param default 
 */
data class PaymentMethodDTO (

    val id: kotlin.String? = null,
    val creditCard: kotlin.String,
    val expiryDate: java.time.LocalDate,
    val owner: kotlin.String,
    val default: kotlin.Boolean? = null
) {
}