import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.components.ImageSelectorComponent
import com.enterprise.android_app.view.components.ViewImage
import com.enterprise.android_app.view_models.ProductCategoryViewModel
import com.enterprise.android_app.view_models.SizeViewModel
import io.swagger.client.models.ClothingDTO
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.EntertainmentDTO
import io.swagger.client.models.HomeDTO
import io.swagger.client.models.ProductDTO
import java.time.LocalDateTime

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductPage() {

    val categoryViewModel = remember { ProductCategoryViewModel() }
    val sizeViewModel = remember { SizeViewModel() }

    var productCost: CustomMoneyDTO = CustomMoneyDTO(0.0, null)
    var deliveryCost: CustomMoneyDTO = CustomMoneyDTO(0.0, null)
    var product: ProductDTO = ProductDTO("1", "", "", productCost, deliveryCost, "", null, 0, null, null, 0, LocalDateTime.now(), null, null, null, null, null, null, "")
    var titleText by remember { mutableStateOf(TextFieldValue(product.title ?: "")) }
    var descriptionText by remember { mutableStateOf(TextFieldValue(product.description ?: "")) }
    var brandText by remember { mutableStateOf(TextFieldValue(product.brand ?: "")) }
    var priceText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCondition by remember { mutableStateOf("") }
    var selectedVisibility by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("") }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    categoryViewModel.getCategories()
    var primaryCategories = categoryViewModel.primaryCategories.collectAsState(initial = emptyList());

    sizeViewModel.getSizes()

    /*var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )*/

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Upload up to 5 photos",
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f))
            ImageSelectorComponent(imageUri, { imageUri = it })
        }
        ImagesContainer(imageUri)

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
            DropDown(selectedCondition, { selectedCondition = it }, ProductDTO.Condition.values().map { it.name }, "Condition")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Visibility")
            Spacer(modifier = Modifier.width(16.dp))
            DropDown(selectedVisibility, { selectedVisibility = it }, ProductDTO.Visibility.values().map { it.name }, "Visibility")
        }
        Spacer(modifier = Modifier.height(16.dp))

        CategoriesRow(primaryCategories, categoryViewModel, sizeViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Price")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = priceText,
                onValueChange = { priceText = it },
                label = { Text(text = "Enter a price") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Currency")
            Spacer(modifier = Modifier.width(16.dp))

            DropDown(selectedCurrency, { selectedCurrency = it }, CustomMoneyDTO.Currency.values().map { it.name }, "Currency")
        }

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 16.dp)) {
            Button(onClick = { /*TODO*/ }) {
                Text("Load product")
            }
        }
    }
}


@Composable
fun ImagesContainer(imageUri: Uri?) {
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
        item(imageUri) {
            val context = LocalContext.current
            val bitmap =  remember {
                mutableStateOf<Bitmap?>(null)
            }

            imageUri.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver,it)

                } else {
                    val source = it?.let { it1 ->
                        ImageDecoder
                            .createSource(context.contentResolver, it1)
                    }
                    bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }
                }
                bitmap.value?.let { it1 -> BoxImage(btm = it1) }
            }
        }
    }
}

@Composable
fun BoxImage(btm: Bitmap) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(bitmap = btm.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 4.dp))
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
fun DropDown(selectedOptionText: String, onChange: (String) -> Unit, options: List<String>, label: String) {
    var expanded by remember { mutableStateOf(false) }
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
            label = { Text("$label") },
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
                        onChange(selectionOption)
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
fun DropDownCategory(selectedOptionText: String, onChange: (String) -> Unit, list: State<List<String>>, label: String) {
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
            label = { Text("$label") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            list.value.forEach { selectionOption ->
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
fun CategoriesRow(primaryCat: State<List<String>>, categoriesViewModel: ProductCategoryViewModel, sizesViewModel: SizeViewModel) {
    var selectedPrimaryCategory by remember { mutableStateOf("") }
    var selectedSecondaryCategory by remember { mutableStateOf("") }
    var selectedTertiaryCategory by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }
    var selectedSize by remember { mutableStateOf("") }
    var selectedMaterial by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Categories")
        DropDownCategory(selectedPrimaryCategory, {selectedPrimaryCategory = it}, list = primaryCat, "Category")
    }
    if(selectedPrimaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getSecondaryCategories(selectedPrimaryCategory)
        var secondaryCategories = categoriesViewModel.secondaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedSecondaryCategory, {selectedSecondaryCategory = it}, list = secondaryCategories, "Category")
        }
    }

    if(selectedSecondaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getTertiaryCategories(selectedSecondaryCategory)
        var tertiaryCategories = categoriesViewModel.tertiaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedTertiaryCategory, { selectedTertiaryCategory = it }, list = tertiaryCategories, "Category")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
    if(selectedTertiaryCategory != "") {
        if(selectedPrimaryCategory == "Home") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Material", modifier = Modifier.weight(1f))
                DropDown(selectedMaterial, { selectedMaterial = it }, HomeDTO.HomeMaterial.values().map { it.name }, "Material")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Color", modifier = Modifier.weight(1f))
                DropDown(selectedColor, { selectedColor = it }, HomeDTO.Colour.values().map { it.name }, "Color")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                sizesViewModel.getSizesByCategory("Home")
                Text("Size", modifier = Modifier.weight(1f))
                DropDownCategory(selectedSize, { selectedSize = it }, sizesViewModel.sizesByCat.collectAsState(initial = emptyList()), "Size")
            }
        }
        else if(selectedPrimaryCategory == "Entertainment") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Language", modifier = Modifier.weight(1f))
                DropDown(selectedLanguage, { selectedLanguage = it }, EntertainmentDTO.EntertainmentLanguage.values().map { it.name }, "Language")
            }
        }
        else if(selectedPrimaryCategory == "Clothing") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Gender", modifier = Modifier.weight(1f))
                DropDown(selectedGender, { selectedGender = it }, ClothingDTO.ProductGender.values().map { it.name }, "Gender")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Color", modifier = Modifier.weight(1f))
                DropDown(selectedColor, { selectedColor = it }, ClothingDTO.Colour.values().map { it.name }, "Color")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                sizesViewModel.getSizesByCategory(selectedSecondaryCategory)
                Text("Size", modifier = Modifier.weight(1f))
                DropDownCategory(selectedSize, { selectedSize = it }, sizesViewModel.sizesByCat.collectAsState(emptyList()), "Size")
            }
        }
    }
}


@Preview
@Composable
fun NewProductPagePreview() {
    AndroidappTheme {
        NewProductPage()
    }
}