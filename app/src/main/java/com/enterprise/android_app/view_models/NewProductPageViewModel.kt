import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File
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
                images.forEach { uri ->
                    uploadImage(uri, context, newProduct)
                }
                //Toast.makeText(context, "Product created", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                //Toast.makeText(context, "Error creating the product", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }

    fun uploadImage(uri: Uri?, context: Context, newProduct: ProductDTO) {

        if (uri != null) {
            val file: File = File(uri.path)
            println("file:" + file)
            println("id: " + newProduct.id)

            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val image: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestBody)

            val retrofit = Retrofit.Builder().baseUrl("http://localhost:8080/api/v1/images/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(uploadService::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                newProduct.id?.let { newProduct.description?.let { it1 ->
                    retrofit.uploadImg(image, it, it1) }
                }
            }
        }
    }
}

interface uploadService {

    @Multipart
    @POST("product")
    suspend fun uploadImg(
        @Part image: MultipartBody.Part,
        @Query("product_id") productId: String,
        @Query("description") description: String
    ): ResponseBody
}


