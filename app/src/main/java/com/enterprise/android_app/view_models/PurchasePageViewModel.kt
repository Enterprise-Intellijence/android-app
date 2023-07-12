import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.swagger.client.apis.OrderControllerApi
import io.swagger.client.apis.TransactionControllerApi
import io.swagger.client.models.OrderBasicDTO
import io.swagger.client.models.OrderCreateDTO
import io.swagger.client.models.OrderDTO
import io.swagger.client.models.PaymentMethodBasicDTO
import io.swagger.client.models.PaymentMethodDTO
import io.swagger.client.models.TransactionCreateDTO
import io.swagger.client.models.TransactionDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PurchasePageViewModel: ViewModel() {

    private var orderControllerApi: OrderControllerApi = OrderControllerApi()
    private var transactionControllerApi: TransactionControllerApi = TransactionControllerApi()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun createOrder(order: OrderCreateDTO?, context: Context): OrderDTO? {
        println("order: $order")
        var created: OrderDTO? = null
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    created = orderControllerApi.createOrder(order!!)
                    println("final order: " + created)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return created
    }

    fun createTransaction(transaction: TransactionCreateDTO): TransactionDTO? {
        var created: TransactionDTO? = null
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    created = transactionControllerApi.createTransaction(transaction)
                    println("final order: " + created)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return created
    }

    fun toOrderBasicDTO(order: OrderDTO): OrderBasicDTO {
        return OrderBasicDTO(
            id = order.id,
            state = OrderBasicDTO.State.valueOf(order.state.name),
            orderDate = order.orderDate,
            orderUpdateDate = order.orderUpdateDate,
            product = order.product,
            offer = order.offer
        )
    }

    fun toPaymentBasicDTO(payment: PaymentMethodDTO): PaymentMethodBasicDTO  {
        return PaymentMethodBasicDTO(
            id = payment.id,
            creditCard = payment.creditCard,
            isDefault = payment.isDefault
        )
    }
}