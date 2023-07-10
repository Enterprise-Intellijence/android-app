import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.apis.TransactionControllerApi
import io.swagger.client.models.OrderCreateDTO
import io.swagger.client.models.OrderDTO
import io.swagger.client.models.TransactionCreateDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PurchasePageViewModel: ViewModel() {

    private var orderControllerApi: OrderControllerApi = OrderControllerApi()
    private var transactionControllerApi: TransactionControllerApi = TransactionControllerApi()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun createOrder(order: OrderCreateDTO?, context: Context) {
        println("order: $order")
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    if (order != null) {
                        var finalOrder: OrderDTO = orderControllerApi.createOrder(order)
                        println("final order: " + finalOrder)

                        //Toast.makeText(context, "Order created", Toast.LENGTH_LONG).show()

                        /*var transaction: TransactionCreateDTO = TransactionCreateDTO(finalOrder.)
                        transactionControllerApi.createTransaction()*/
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}