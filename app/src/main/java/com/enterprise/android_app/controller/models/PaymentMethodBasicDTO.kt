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
 * @param default 
 */
data class PaymentMethodBasicDTO (

    val id: kotlin.String,
    val creditCard: kotlin.String,
    val isDefault: kotlin.Boolean? = null
) {
}