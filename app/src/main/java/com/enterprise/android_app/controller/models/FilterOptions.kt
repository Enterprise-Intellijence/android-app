package com.enterprise.android_app.controller.models

import com.enterprise.android_app.view.screen.filter.Condition
import io.swagger.client.models.ProductCategory
import java.time.LocalDate
import java.time.LocalDateTime

data class FilterOptions(
    var title: String? = null,
    var description: String? = null,
    var minProductCost: Number? = null,
    var maxProductCost: Number? = null,
    var brands: Array<String>? = null,
    var condition: Array<Condition>? = null,
    var views: Number? = null,
    var userId: String? = null,
    var uploadDate: LocalDateTime? = null,
    var availability: String? = null,
    var productCategory: ProductCategory? = null,
    var primaryCat: String? = null,
    var secondaryCat: String? = null,
    var tertiaryCat: String? = null,
    var likesNumber: Number? = null,
    var productGender: String? = null,
    var sizes: Array<String>? = null,
    var colour: String? = null,
    var entertainmentLanguage: String? = null,
    var homeMaterial: String? = null,
    var page: Number? = null,
    var sizePage: Number? = null,
    var sortBy: String? = null,
    var sortDirection: String? = null
)