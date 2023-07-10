import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.enterprise.android_app.controller.BasePath
import com.enterprise.android_app.model.CurrentDataUtils
import io.swagger.client.apis.ImageControllerApi
import io.swagger.client.apis.ProductControllerApi
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File
import java.io.InputStream

class NewProductPageViewModel: ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val productControllerApi = ProductControllerApi()
    val imageControllerApi = ImageControllerApi()
    val images = mutableStateListOf<Uri?>()
    val imageStreamList = mutableStateListOf<InputStream>()


    var product = mutableStateOf(null)


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun saveNewProduct(product: ProductCreateDTO, context: Context, images: List<Uri?>) {
        println("product: " + product)

        coroutineScope.launch {
            try {
                val newProduct = withContext(Dispatchers.IO) {
                    productControllerApi.createProduct(product)
                }
                println("proddddd: " + newProduct)
                for (i in images.indices) {
                    uploadImage(images[i], imageStreamList[i], newProduct)
                }

                //Toast.makeText(context, "Product created", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                //Toast.makeText(context, "Error creating the product", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }
    }

    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun uploadImage(uri: Uri?, img: InputStream, newProduct: ProductDTO) {

        var file: File? = null
        if (uri != null)
            file = File(uri.path!!)

        println("file:" + file)
        println("id: " + newProduct.id)

        /*val part = MultipartBody.Part.createFormData(
            "file", file?.name, RequestBody.create(
                "multipart/form-data".toMediaTypeOrNull(),
                img.readBytes()
            )
        )

        val part = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "multipartFile",
                file?.name,
                RequestBody.create("image".toMediaTypeOrNull(), img.readBytes())
            )
            .build()*/

        val client = OkHttpClient()

        val urlBuilder =
            (BasePath.BASE_PATH + "/api/v1/images/product").toHttpUrlOrNull()?.newBuilder()

        urlBuilder!!.addQueryParameter("product_id", newProduct.id)
        urlBuilder.addQueryParameter("description", newProduct.description)
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


    /*coroutineScope.launch {
            try {
                val res = withContext(Dispatchers.IO) {
                    imageControllerApi.saveImageProduct(
                        part,
                        newProduct.id!!,
                        newProduct.description!!
                    )
                }
                println("response: " + res)
            } catch (e: Exception) {
                println("ERROREEEEE")
                e.printStackTrace()
            }
        }
    }
}*/