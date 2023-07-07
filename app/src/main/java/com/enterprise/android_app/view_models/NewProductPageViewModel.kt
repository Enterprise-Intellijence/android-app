import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class NewProductPageViewModel: ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val productControllerApi = ProductControllerApi()
    val imageControllerApi = ImageControllerApi()
    val images = mutableStateListOf<Uri?>()
    
    var product = mutableStateOf(null)



    fun saveNewProduct(product: ProductCreateDTO, context: Context, images: List<Uri?>) {
        println("product: " + product)

        coroutineScope.launch {
            try {
                val newProduct = withContext(Dispatchers.IO) {
                    productControllerApi.createProduct(product)
                }
                println("proddddd: " + newProduct)
                images.forEach {uri ->
                    uploadImage(uri, context, newProduct)
                }
                //Toast.makeText(context, "Product created", Toast.LENGTH_LONG).show()

            }
            catch(e: Exception) {
                //Toast.makeText(context, "Error creating the product", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    fun uriImageToByteArray(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }

    fun uploadImage(uri: Uri?, context: Context, newProduct: ProductDTO) {

        val image = uri?.let { uriImageToByteArray(context, it) }
        coroutineScope.launch {
            try {
                newProduct.id?.let { newProduct.description?.let { it1 ->
                    imageControllerApi.saveImageProduct(image, it, it1) }
                }
            }
            catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


