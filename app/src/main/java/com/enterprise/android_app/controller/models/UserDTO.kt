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
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.UserImageDTO

/**
 * 
 * @param id 
 * @param username 
 * @param email 
 * @param bio 
 * @param photoProfile 
 * @param provider 
 * @param status 
 * @param addresses 
 * @param paymentMethods 
 * @param role 
 * @param reviewsTotalSum 
 * @param reviewsNumber 
 * @param followersNumber 
 * @param followingNumber 
 */
data class UserDTO (

    val id: kotlin.String,
    val username: kotlin.String,
    val email: kotlin.String,
    val bio: kotlin.String? = null,
    val photoProfile: UserImageDTO? = null,
    val provider: Provider,
    val status: Status,
    val addresses: kotlin.Array<AddressDTO>? = null,
    val paymentMethods: kotlin.Array<PaymentMethodDTO>? = null,
    val role: Role,
    val reviewsTotalSum: kotlin.Int? = null,
    val reviewsNumber: kotlin.Int? = null,
    val followersNumber: kotlin.Int? = null,
    val followingNumber: kotlin.Int? = null
) {
    /**
    * 
    * Values: LOCAL,GOOGLE
    */
    enum class Provider(val value: kotlin.String){
        LOCAL("LOCAL"),
        GOOGLE("GOOGLE");
    }
    /**
    * 
    * Values: ACTIVE,BANNED,HIDDEN,HOLIDAY,CANCELLED
    */
    enum class Status(val value: kotlin.String){
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        HIDDEN("HIDDEN"),
        HOLIDAY("HOLIDAY"),
        CANCELLED("CANCELLED");
    }
    /**
    * 
    * Values: ADMIN,USER,SUPERADMIN
    */
    enum class Role(val value: kotlin.String){
        ADMIN("ADMIN"),
        USER("USER"),
        SUPERADMIN("SUPER_ADMIN");
    }
}