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

import io.swagger.client.models.CurrencyUnit

/**
 * 
 * @param zero 
 * @param negative 
 * @param positive 
 * @param amount 
 * @param scale 
 * @param currencyUnit 
 * @param amountMajorInt 
 * @param amountMinorLong 
 * @param amountMajorLong 
 * @param amountMinor 
 * @param minorPart 
 * @param positiveOrZero 
 * @param amountMinorInt 
 * @param amountMajor 
 * @param negativeOrZero 
 */
data class Money (

    val zero: kotlin.Boolean? = null,
    val negative: kotlin.Boolean? = null,
    val positive: kotlin.Boolean? = null,
    val amount: java.math.BigDecimal? = null,
    val scale: kotlin.Int? = null,
    val currencyUnit: CurrencyUnit? = null,
    val amountMajorInt: kotlin.Int? = null,
    val amountMinorLong: kotlin.Long? = null,
    val amountMajorLong: kotlin.Long? = null,
    val amountMinor: java.math.BigDecimal? = null,
    val minorPart: kotlin.Int? = null,
    val positiveOrZero: kotlin.Boolean? = null,
    val amountMinorInt: kotlin.Int? = null,
    val amountMajor: java.math.BigDecimal? = null,
    val negativeOrZero: kotlin.Boolean? = null
) {
}