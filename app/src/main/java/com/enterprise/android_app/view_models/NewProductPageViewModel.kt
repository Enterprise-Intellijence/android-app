import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.model.UploadStreamRequestBody
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


class NewProductPageViewModel : ViewModel() {

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

        if (uri == null) {
            throw IllegalArgumentException("Cannot upload file with null uri")
        }
        if (uri.path == null) {
            throw IllegalArgumentException("Cannot upload file with null path")
        }
        Log.d("new product view model", "path ${uri.path}")

        val stream = context.contentResolver.openInputStream(uri) ?: return
        val request = UploadStreamRequestBody("image/*", stream, onUploadProgress = {
            Log.d("new product view model", "On upload progress $it")
        })

        val filePart = MultipartBody.Part.createFormData(
            "file",
            "test.jpg",
            request
        )


//
//
//        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//        val image: MultipartBody.Part =
//            MultipartBody.Part.createFormData("file", file.name, requestBody)

        val retrofit = Retrofit.Builder()
            .baseUrl(BasePath.BASE_PATH + "/api/v1/images/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UploadService::class.java)

        val coroutineExceptionHandler = CoroutineExceptionHandler(){_, throwable ->
            throwable.printStackTrace()
        }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            newProduct.id?.let {
                newProduct.description?.let { it1 ->
                    retrofit.uploadImg(filePart, it, it1)
                }
            }
        }
    }
}

interface UploadService {

    @Multipart
    @POST("product")
    suspend fun uploadImg(
        @Part image: MultipartBody.Part,
        @Query("product_id") productId: String,
        @Query("description") description: String
    ): ResponseBody
}


