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
import io.swagger.client.models.Offer
import io.swagger.client.models.Order
import io.swagger.client.models.ProductCategory
import io.swagger.client.models.ProductImage
import io.swagger.client.models.Report
import io.swagger.client.models.User

/**
 * 
 * @param id 
 * @param title 
 * @param description 
 * @param productCost 
 * @param deliveryCost 
 * @param brand 
 * @param condition 
 * @param productSize 
 * @param views 
 * @param uploadDate 
 * @param lastUpdateDate 
 * @param visibility 
 * @param availability 
 * @param productCategory 
 * @param likesNumber 
 * @param seller 
 * @param usersThatLiked 
 * @param offers 
 * @param messages 
 * @param order 
 * @param productImages 
 * @param reports 
 */
data class Product (

    val id: kotlin.String? = null,
    val title: kotlin.String? = null,
    val description: kotlin.String? = null,
    val productCost: CustomMoney? = null,
    val deliveryCost: CustomMoney? = null,
    val brand: kotlin.String? = null,
    val condition: Condition? = null,
    val productSize: ProductSize? = null,
    val views: kotlin.Int? = null,
    val uploadDate: java.time.LocalDateTime? = null,
    val lastUpdateDate: java.time.LocalDateTime? = null,
    val visibility: Visibility? = null,
    val availability: Availability? = null,
    val productCategory: ProductCategory? = null,
    val likesNumber: kotlin.Int? = null,
    val seller: User? = null,
    val usersThatLiked: kotlin.Array<User>? = null,
    val offers: kotlin.Array<Offer>? = null,
    val messages: kotlin.Array<Message>? = null,
    val order: Order? = null,
    val productImages: kotlin.Array<ProductImage>? = null,
    val reports: kotlin.Array<Report>? = null
) {
    /**
    * 
    * Values: NEWWITHTAG,NEWWITHOUTTAG,VERYGOOD,GOOD,ACCEPTABLE
    */
    enum class Condition(val value: kotlin.String){
        NEWWITHTAG("NEW_WITH_TAG"),
        NEWWITHOUTTAG("NEW_WITHOUT_TAG"),
        VERYGOOD("VERY_GOOD"),
        GOOD("GOOD"),
        ACCEPTABLE("ACCEPTABLE");
    }
    /**
    * 
    * Values: BIG,MEDIUM,SMALL
    */
    enum class ProductSize(val value: kotlin.String){
        BIG("BIG"),
        MEDIUM("MEDIUM"),
        SMALL("SMALL");
    }
    /**
    * 
    * Values: PUBLIC,PRIVATE
    */
    enum class Visibility(val value: kotlin.String){
        PUBLIC("PUBLIC"),
        PRIVATE("PRIVATE");
    }
    /**
    * 
    * Values: AVAILABLE,PENDING,UNAVAILABLE
    */
    enum class Availability(val value: kotlin.String){
        AVAILABLE("AVAILABLE"),
        PENDING("PENDING"),
        UNAVAILABLE("UNAVAILABLE");
    }
}