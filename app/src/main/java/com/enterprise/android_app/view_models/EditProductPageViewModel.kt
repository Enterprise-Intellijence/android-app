import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ProductControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProductPageViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val productControllerApi: ProductControllerApi = ProductControllerApi()


    fun deleteProduct(id: String) {
        coroutineScope.launch {
            try {
                val prod = withContext(Dispatchers.IO) {
                    productControllerApi.deleteProduct(id)
                }
                println("prod: "+ prod)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}