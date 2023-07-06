import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view_models.ProductCategoryViewModel
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.ProductDTO
import java.time.LocalDateTime

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductPage() {

    val categoryViewModel = remember { ProductCategoryViewModel() }

    var productCost: CustomMoneyDTO = CustomMoneyDTO(0.0, null)
    var deliveryCost: CustomMoneyDTO = CustomMoneyDTO(0.0, null)
    var product: ProductDTO = ProductDTO("1", "", "", productCost, deliveryCost, "", null, 0, null, null, 0, LocalDateTime.now(), null, null, null, null, null, null, "")
    var titleText by remember { mutableStateOf(TextFieldValue(product.title ?: "")) }
    var descriptionText by remember { mutableStateOf(TextFieldValue(product.description ?: "")) }
    var brandText by remember { mutableStateOf(TextFieldValue(product.brand ?: "")) }

    categoryViewModel.getCategories()
    var primaryCategories = categoryViewModel.primaryCategories.collectAsState(initial = emptyList());


    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Upload up to 5 photos",
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Upload photo")
            }
        }
        ImagesContainer()
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Title")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = titleText,
                onValueChange = { titleText = it },
                label = {
                    Text(text = "Enter title")},
                    modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Description")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = descriptionText,
                onValueChange = { descriptionText = it },
                label = { Text(text = "Enter description") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Brand")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = brandText,
                onValueChange = { brandText = it },
                label = { Text(text = "Enter a brand") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Condition")
            Spacer(modifier = Modifier.width(16.dp))
            DropDownCondition()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Visibility")
            Spacer(modifier = Modifier.width(16.dp))
            DropDownVisibility()
        }
        Spacer(modifier = Modifier.height(16.dp))


        CategoriesRow(primaryCategories, categoryViewModel)

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 16.dp)) {
            Button(onClick = { /*TODO*/ }) {
            Text("Load product")
            }
        }
    }
}

@Composable
fun ImagesContainer() {
    LazyRow(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.White)
            .border(
                BorderStroke(2.dp, Color.Black),
                shape = MaterialTheme.shapes.small
            )
    ) {

    }
}

@Composable
fun BoxImage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = com.gowtham.ratingbar.R.drawable.abc_cab_background_top_mtrl_alpha),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
                .align(Alignment.TopEnd)
        ) {
            IconToggleButton(
                checked = false,
                onCheckedChange = { /* Handle icon click event */ },
                modifier = Modifier.fillMaxSize(),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownVisibility() {
    val options = ProductDTO.Visibility.values().map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Visibility") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCondition() {
    val options = ProductDTO.Condition.values().map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Condition") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCategory(selectedOptionText: String, onChange: (String) -> Unit, categories: State<List<String>>) {
    var expanded by remember { mutableStateOf(false) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            categories.value.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onChange(selectionOption)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun CategoriesRow(primaryCat: State<List<String>>, categoriesViewModel: ProductCategoryViewModel) {
    var selectedPrimaryCategory by remember { mutableStateOf("") }
    var selectedSecondaryCategory by remember { mutableStateOf("") }
    var selectedTertiaryCategory by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Categories")
        DropDownCategory(selectedPrimaryCategory, {selectedPrimaryCategory = it}, categories = primaryCat)
    }
    if(selectedPrimaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getSecondaryCategories(selectedPrimaryCategory)
        var secondaryCategories = categoriesViewModel.secondaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedSecondaryCategory, {selectedSecondaryCategory = it}, categories = secondaryCategories)
        }
    }

    if(selectedSecondaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getTertiaryCategories(selectedSecondaryCategory)
        var tertiaryCategories = categoriesViewModel.tertiaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedTertiaryCategory, { selectedTertiaryCategory = it }, categories = tertiaryCategories)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun PreviewImageContainer() {
    ImagesContainer()
}

@Preview
@Composable
fun PreviewBoxImage() {
    BoxImage()
}


@Preview
@Composable
fun NewProductPagePreview() {
    AndroidappTheme {
        NewProductPage()
    }
}