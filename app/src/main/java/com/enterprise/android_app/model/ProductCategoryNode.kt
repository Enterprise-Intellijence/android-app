

class ProductCategoryNode(
    var rawName: String,
    var name: String,
    var parentCategory: ProductCategoryNode?,
    var childCategories: ArrayList<ProductCategoryNode>?
) {

    init {
        childCategories = childCategories ?: ArrayList()
    }

    fun addChildCategory(category: ProductCategoryNode) {
        childCategories?.add(category)
    }
}