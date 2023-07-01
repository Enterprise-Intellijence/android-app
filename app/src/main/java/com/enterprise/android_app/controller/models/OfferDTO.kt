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
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.UserBasicDTO

/**
 * 
 * @param id 
 * @param amount 
 * @param creationTime 
 * @param state 
 * @param offerer 
 * @param product 
 * @param order 
 */
data class OfferDTO (

    val id: kotlin.String? = null,
    val amount: CustomMoneyDTO? = null,
    val creationTime: java.time.LocalDateTime? = null,
    val state: State? = null,
    val offerer: UserBasicDTO? = null,
    val product: ProductBasicDTO? = null,
    val order: OrderBasicDTO? = null
) {
    /**
    * 
    * Values: PENDING,ACCEPTED,REJECTED
    */
    enum class State(val value: kotlin.String){
        PENDING("PENDING"),
        ACCEPTED("ACCEPTED"),
        REJECTED("REJECTED");
    }
}