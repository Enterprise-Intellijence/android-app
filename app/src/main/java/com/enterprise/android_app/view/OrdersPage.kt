import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.enterprise.android_app.view.components.LazyGridOrderCard
import com.enterprise.android_app.view_models.OrdersViewModel

@Composable
fun OrdersPage(navigation: NavHostController){
    
    val ordersViewModel = remember {OrdersViewModel()}
    val orderList = ordersViewModel.orderList
    val lazyGridState =  rememberLazyGridState()

    LaunchedEffect(key1 = ordersViewModel.currentPage){
        ordersViewModel.loadNextPage()
    }

    if(orderList.isEmpty()){
        CircularProgressIndicator(Modifier.size(40.dp))

    }else{
        LazyGridOrderCard(
            orders = orderList,
            lazyGridState = lazyGridState
        ) {
            ordersViewModel.loadNextPage()
        }
    }
}