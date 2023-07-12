import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProductPageViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val productControllerApi: ProductControllerApi = ProductControllerApi()


    fun deleteProduct(id: String): Boolean {
        var deleted = true
        coroutineScope.launch {
            try {
                val prod = withContext(Dispatchers.IO) {
                    productControllerApi.deleteProduct(id)
                }
                println("prod: "+ prod)

            } catch (e: Exception) {
                deleted = false
                e.printStackTrace()
            }
        }
        return deleted
    }

    fun updateProduct(productId: String, product: ProductDTO): Boolean {
        var updated = true
        coroutineScope.launch {
            try {
                val prod = withContext(Dispatchers.IO) {
                    productControllerApi.updateProduct(product, productId)
                }
                println("prod: "+ prod)

            } catch (e: Exception) {
                updated = false
                e.printStackTrace()
            }
        }
        return updated
    }
}