import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enterprise.android_app.navigation.Navigation

@Composable
fun EditProductPage(navController: NavHostController, productId: String) {

    val editProductPageViewModel: EditProductPageViewModel = viewModel()
    val context = LocalContext.current

    Column() {
        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                val deleted = editProductPageViewModel.deleteProduct(productId)
                if(deleted) {
                    mToast(context, "Product deleted")
                    navController.navigate(Navigation.HomePage.route)
                }
                else mToast(context, "Can't delete product with orders active")
                },
            modifier = Modifier.padding(end = 8.dp)) {
                Text(text = "Delete product")
            }
        }
        NewProductPage(navController = navController, productId = productId)
    }

}

private fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}