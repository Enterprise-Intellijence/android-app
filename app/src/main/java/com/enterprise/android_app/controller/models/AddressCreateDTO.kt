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
 * @param header 
 * @param country 
 * @param city 
 * @param street 
 * @param zipCode 
 * @param phoneNumber 
 * @param default 
 */
data class AddressCreateDTO (

    val header: kotlin.String,
    val country: kotlin.String,
    val city: kotlin.String,
    val street: kotlin.String,
    val zipCode: kotlin.String,
    val phoneNumber: kotlin.String,
    val default: kotlin.Boolean
) {
}