import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.model.CurrentDataUtils
import com.google.gson.Gson
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.infrastructure.isClientError
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.InputStream

class NewProductPageViewModel: ViewModel() {

    private val client = OkHttpClient()
    private val gson = Gson()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val productControllerApi = ProductControllerApi()
    val images = mutableStateListOf<Uri?>()
    val imageStreamList = mutableStateMapOf<Uri?, InputStream>()


    var product = mutableStateOf(null)


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun saveNewProduct(product: ProductCreateDTO, context: Context, images: List<Uri?>): ProductDTO? {

        println("produc: " + product.toString())
        coroutineScope.launch {
            try {

                val urlBuilder = (BasePath.BASE_PATH + "/api/v1/products").toHttpUrlOrNull()?.newBuilder()

                val url = urlBuilder!!.build()

                val requestBody = gson.toJson(product).toRequestBody("application/json".toMediaType())


                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("Authorization", CurrentDataUtils.accessToken)
                    .build()
                val response = withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
                println("response: " + response)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Product created", Toast.LENGTH_LONG).show()

                    val responseBody = response.body?.string()
                    println("response: " + responseBody)
                    val idRegex = Regex("\"id\":\"(.+)\",\"title\"")
                    val idMatch = idRegex.find(responseBody!!)
                    val productId: String? = idMatch?.groups?.get(1)?.value
                    println("product res: " + productId)

                    val descrRegex = Regex("\"description\":\"(.+)\",\"productCost\"")
                    val descrMatch = descrRegex.find(responseBody)
                    val productDescr: String? = descrMatch?.groups?.get(1)?.value

                    images.forEach { uri ->
                        uploadImage(uri, imageStreamList[uri]!!, productId, productDescr)
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error on product creation", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun uploadImage(uri: Uri?, img: InputStream, productId: String?, description: String?) {

        var file: File? = null
        if (uri != null)
            file = File(uri.path!!)

        val urlBuilder = (BasePath.BASE_PATH + "/api/v1/images/product").toHttpUrlOrNull()?.newBuilder()

        urlBuilder!!.addQueryParameter("product_id", productId)
        urlBuilder.addQueryParameter("description", description)
        val url = urlBuilder.build()

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "multipartFile",
                file?.name,
                RequestBody.create("image/*".toMediaTypeOrNull(), img.readAllBytes())
            )
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Authorization", CurrentDataUtils.accessToken)
            .build()

        val response = client.newCall(request).execute()
        println("response: " + response)
    }
}