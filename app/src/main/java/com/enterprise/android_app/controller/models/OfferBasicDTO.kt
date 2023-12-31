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

import java.time.LocalDateTime

/**
 * 
 * @param id 
 * @param amount 
 * @param state 
 * @param creationTime 
 */
data class OfferBasicDTO(

    val id: String?,
    val amount: CustomMoneyDTO,
    val state: State?,
    val creationTime: LocalDateTime? = null
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