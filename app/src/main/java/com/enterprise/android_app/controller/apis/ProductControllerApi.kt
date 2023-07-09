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
package io.swagger.client.apis

import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.view.screen.filter.Condition
import io.swagger.client.models.AdminProductsBody
import io.swagger.client.models.CapabilityDTO
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.PageMessageDTO
import io.swagger.client.models.PageOfferBasicDTO
import io.swagger.client.models.PageProductBasicDTO
import io.swagger.client.models.ProductBasicDTO
import io.swagger.client.models.ProductCategory
import io.swagger.client.models.ProductsIdBody
import io.swagger.client.models.ProductsIdBody1

import io.swagger.client.infrastructure.*
import io.swagger.client.models.Product
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import io.swagger.client.models.V1ProductsBody

class ProductControllerApi(basePath: kotlin.String = BasePath.BASE_PATH) : ApiClient(basePath) {

    /**
     * 
     * 
     * @param productId  
     * @return CapabilityDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun createCapability(productId: kotlin.String): CapabilityDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/products/capability/{productId}".replace("{" + "productId" + "}", "$productId")
        )
        val response = request<CapabilityDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as CapabilityDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @return ProductsIdBody1
     */
    @Suppress("UNCHECKED_CAST")
    fun createProduct(body: ProductCreateDTO): ProductDTO {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/api/v1/products"
        )
        val response = request<ProductDTO>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return void
     */
    fun deleteProduct(id: kotlin.String): Unit {
        val localVariableConfig = RequestConfig(
                RequestMethod.DELETE,
                "/api/v1/products/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return List<ProductCategoryDTO>
     */
    @Suppress("UNCHECKED_CAST")
    fun getCategoriesList(): Any {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/categories"
        )
        val response = request<Any>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> {

                (response as Success<*>).data as Any
            }

            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param title  (optional)
     * @param description  (optional)
     * @param minProductCost  (optional)
     * @param maxProductCost  (optional)
     * @param brands  (optional)
     * @param condition  (optional)
     * @param views  (optional)
     * @param userId  (optional)
     * @param uploadDate  (optional)
     * @param availability  (optional)
     * @param productCategory  (optional)
     * @param primaryCat  (optional)
     * @param secondaryCat  (optional)
     * @param tertiaryCat  (optional)
     * @param likesNumber  (optional)
     * @param productGender  (optional)
     * @param sizes  (optional)
     * @param colour  (optional)
     * @param entertainmentLanguage  (optional)
     * @param homeMaterial  (optional)
     * @param page  (optional, default to 0)
     * @param sizePage  (optional, default to 10)
     * @param sortBy  (optional, default to uploadDate)
     * @param sortDirection  (optional, default to DESC)
     * @return PageProductBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getFilteredProducts(title: kotlin.String? = null, description: kotlin.String? = null, minProductCost: kotlin.Double? = null, maxProductCost: kotlin.Double? = null, brands: kotlin.Array<kotlin.String>? = null, condition: kotlin.Array<Condition>? = null, views: kotlin.Int? = null, userId: kotlin.String? = null, uploadDate: java.time.LocalDateTime? = null, availability: kotlin.String? = null, productCategory: ProductCategory? = null, primaryCat: kotlin.String? = null, secondaryCat: kotlin.String? = null, tertiaryCat: kotlin.String? = null, likesNumber: kotlin.Int? = null, productGender: kotlin.String? = null, sizes: kotlin.Array<kotlin.String>? = null, colour: kotlin.String? = null, entertainmentLanguage: kotlin.String? = null, homeMaterial: kotlin.String? = null, page: kotlin.Int? = null, sizePage: kotlin.Int? = null, sortBy: kotlin.String? = null, sortDirection: kotlin.String? = null): PageProductBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (title != null) {
                put("title", listOf(title.toString()))
            }
            if (description != null) {
                put("description", listOf(description.toString()))
            }
            if (minProductCost != null) {
                put("minProductCost", listOf(minProductCost.toString()))
            }
            if (maxProductCost != null) {
                put("maxProductCost", listOf(maxProductCost.toString()))
            }
            if (brands != null) {
                put("brands", toMultiValue(brands.toList(), "multi"))
            }
            if (condition != null) {
                put("condition", toMultiValue(condition.toList(), "multi"))
            }
            if (views != null) {
                put("views", listOf(views.toString()))
            }
            if (userId != null) {
                put("userId", listOf(userId.toString()))
            }
            if (uploadDate != null) {
                put("uploadDate", listOf(uploadDate.toString()))
            }
            if (availability != null) {
                put("availability", listOf(availability.toString()))
            }
            if (productCategory != null) {
                put("productCategory", listOf(productCategory.toString()))
            }
            if (primaryCat != null) {
                put("primaryCat", listOf(primaryCat.toString()))
            }
            if (secondaryCat != null) {
                put("secondaryCat", listOf(secondaryCat.toString()))
            }
            if (tertiaryCat != null) {
                put("tertiaryCat", listOf(tertiaryCat.toString()))
            }
            if (likesNumber != null) {
                put("likesNumber", listOf(likesNumber.toString()))
            }
            if (productGender != null) {
                put("productGender", listOf(productGender.toString()))
            }
            if (sizes != null) {
                put("sizes", toMultiValue(sizes.toList(), "multi"))
            }
            if (colour != null) {
                put("colour", listOf(colour.toString()))
            }
            if (entertainmentLanguage != null) {
                put("entertainmentLanguage", listOf(entertainmentLanguage.toString()))
            }
            if (homeMaterial != null) {
                put("homeMaterial", listOf(homeMaterial.toString()))
            }
            if (page != null) {
                put("page", listOf(page.toString()))
            }
            if (sizePage != null) {
                put("sizePage", listOf(sizePage.toString()))
            }
            if (sortBy != null) {
                put("sortBy", listOf(sortBy.toString()))
            }
            if (sortDirection != null) {
                put("sortDirection", listOf(sortDirection.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/filter", query = localVariableQuery
        )
        val response = request<PageProductBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageProductBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param page  (optional, default to 0)
     * @param size  (optional, default to 10)
     * @return PageProductBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getMyProducts(page: kotlin.Int? = null, size: kotlin.Int? = null): PageProductBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            if (page != null) {
                put("page", listOf(page.toString()))
            }
            if (size != null) {
                put("size", listOf(size.toString()))
            }
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/me", query = localVariableQuery
        )
        val response = request<PageProductBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageProductBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getPrimaryCategoriesList(): kotlin.Any {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/products/primaryCategories"
        )
        val response = request<kotlin.Any>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param primary
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getSecondaryCategoriesList(primary: kotlin.String): kotlin.Any {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/products/secondaryCategories/{primary}".replace("{" + "primary" + "}", "$primary")
        )
        val response = request<kotlin.Any>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param secondary
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getTertiaryCategoriesList(secondary: kotlin.String): kotlin.Any {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/products/tertiaryCategories/{secondary}".replace("{" + "secondary" + "}", "$secondary")
        )
        val response = request<kotlin.Any>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param category
     * @return kotlin.String
     */
    @Suppress("UNCHECKED_CAST")
    fun getCategoryId(category: kotlin.String): kotlin.String {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/products/category/{category}".replace("{" + "category" + "}", "$category")
        )
        val response = request<kotlin.String>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.String
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     *
     *
     * @param category
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getSizesListByCategory(category: kotlin.String): kotlin.Any {
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/api/v1/products/sizes/{category}".replace("{" + "category" + "}", "$category")
        )
        val response = request<kotlin.Any>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param token  
     * @return AdminProductsBody
     */
    @Suppress("UNCHECKED_CAST")
    fun getProductFromCapability(token: kotlin.String): AdminProductsBody {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/capability/{token}".replace("{" + "token" + "}", "$token")
        )
        val response = request<AdminProductsBody>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as AdminProductsBody
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getProductGender(): kotlin.Any {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/categories/clothing/gender"
        )
        val response = request<kotlin.Any>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @param page  
     * @param size  
     * @return PageMessageDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getProductMessages(id: kotlin.String, page: kotlin.Int, size: kotlin.Int): PageMessageDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/{id}/messages".replace("{" + "id" + "}", "$id"), query = localVariableQuery
        )
        val response = request<PageMessageDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageMessageDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @param page  
     * @param size  
     * @return PageOfferBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getProductOffers(id: kotlin.String, page: kotlin.Int, size: kotlin.Int): PageOfferBasicDTO {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/{id}/offers".replace("{" + "id" + "}", "$id"), query = localVariableQuery
        )
        val response = request<PageOfferBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as PageOfferBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return OrderBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun getProductOrder(id: kotlin.String): OrderBasicDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/{id}/order".replace("{" + "id" + "}", "$id")
        )
        val response = request<OrderBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as OrderBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getSizesList(): kotlin.Any {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/sizes"
        )
        val response = request<kotlin.Any>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param productId  
     * @param page  
     * @param size  
     * @return kotlin.Any
     */
    @Suppress("UNCHECKED_CAST")
    fun getUsersThatLikedProduct(productId: kotlin.String, page: kotlin.Int, size: kotlin.Int): kotlin.Any {
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, kotlin.collections.List<kotlin.String>>().apply {
            put("page", listOf(page.toString()))
            put("size", listOf(size.toString()))
        }
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/likes/users/{productId}".replace("{" + "productId" + "}", "$productId"), query = localVariableQuery
        )
        val response = request<kotlin.Any>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as kotlin.Any
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return ProductBasicDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun productBasicById(id: kotlin.String): ProductBasicDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/basic/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ProductBasicDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductBasicDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param id  
     * @return ProductDTO
     */
    @Suppress("UNCHECKED_CAST")
    fun productById(id: kotlin.String): ProductDTO {
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/api/v1/products/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ProductDTO>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductDTO
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param id  
     * @return ProductsIdBody
     */
    @Suppress("UNCHECKED_CAST")
    fun replaceProduct(body: ProductsIdBody, id: kotlin.String): ProductsIdBody {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PUT,
                "/api/v1/products/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ProductsIdBody>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductsIdBody
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
    /**
     * 
     * 
     * @param body  
     * @param id  
     * @return ProductsIdBody1
     */
    @Suppress("UNCHECKED_CAST")
    fun updateProduct1(body: ProductsIdBody1, id: kotlin.String): ProductsIdBody1 {
        val localVariableBody: kotlin.Any? = body
        val localVariableConfig = RequestConfig(
                RequestMethod.PATCH,
                "/api/v1/products/{id}".replace("{" + "id" + "}", "$id")
        )
        val response = request<ProductsIdBody1>(
                localVariableConfig, localVariableBody
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProductsIdBody1
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message ?: "Server error")
        }
    }
}
