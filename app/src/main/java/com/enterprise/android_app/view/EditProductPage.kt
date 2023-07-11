import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun EditProductPage(navController: NavHostController, productId: String) {
    println("product id: " + productId)
    Column() {
        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { /*TODO*/ },
            modifier = Modifier.padding(end = 8.dp)) {
                Text(text = "Delete product")
            }
        }
        NewProductPage(navController = navController, productId = productId)
    }

}