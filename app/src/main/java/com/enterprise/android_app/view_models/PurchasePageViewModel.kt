import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.models.OrderCreateDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PurchasePageViewModel: ViewModel() {

    private var orderControllerApi: OrderControllerApi = OrderControllerApi()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun createOrder(order: OrderCreateDTO?) {
        println("order: $order")
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    if (order != null) {
                        orderControllerApi.createOrder(order)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}