import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enterprise.android_app.ui.theme.AndroidappTheme
import com.enterprise.android_app.view.components.ImageSelectorComponent
import com.enterprise.android_app.view_models.ProductCategoryViewModel
import com.enterprise.android_app.view_models.SizeViewModel
import io.swagger.client.models.ClothingCreateDTO
import io.swagger.client.models.CustomMoneyDTO
import io.swagger.client.models.EntertainmentCreateDTO
import io.swagger.client.models.HomeCreateDTO
import io.swagger.client.models.ProductCategoryDTO
import io.swagger.client.models.ProductCreateDTO
import io.swagger.client.models.ProductDTO
import io.swagger.client.models.SizeDTO
import java.io.InputStream
import java.util.stream.Stream

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductPage(navController: NavHostController, productId: String) {
    val context = LocalContext.current

    val categoryViewModel: ProductCategoryViewModel = viewModel()
    val sizeViewModel: SizeViewModel = viewModel()
    val newProductViewModel: NewProductPageViewModel = viewModel()

    LaunchedEffect(key1 = "new product page") {
        if(productId != "") {
            newProductViewModel.getProductById(productId)
        }
    }

    val titleText = newProductViewModel.titleText
    val descriptionText = newProductViewModel.descriptionText
    val brandText = newProductViewModel.brandText
    val priceText = newProductViewModel.priceText
    val deliveryPriceText = newProductViewModel.deliveryPriceText
    val selectedCondition = newProductViewModel.selectedCondition
    val selectedVisibility = newProductViewModel.selectedVisibility
    val selectedCurrency = newProductViewModel.selectedCurrency
    val selectedDeliverySize = newProductViewModel.selectedDeliverySize

    val selectedPrimaryCategory = newProductViewModel.selectedPrimaryCategory
    val selectedSecondaryCategory = newProductViewModel.selectedSecondaryCategory
    val selectedTertiaryCategory = newProductViewModel.selectedTertiaryCategory
    val selectedLanguage = newProductViewModel.selectedLanguage
    val selectedColor = newProductViewModel.selectedColor
    val selectedSize = newProductViewModel.selectedSize
    val selectedMaterial = newProductViewModel.selectedMaterial
    val selectedGender = newProductViewModel.selectedGender

    val imagesUri = newProductViewModel.images
    val imageStream = newProductViewModel.imageStreamList

    categoryViewModel.getCategories()
    var primaryCategories = categoryViewModel.primaryCategories.collectAsState(initial = emptyList())

    sizeViewModel.getSizes()

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Upload up to 5 photos",
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f))
            if(imagesUri.size < 5) {
                ImageSelectorComponent {uri, stream ->
                    imagesUri.add(uri)
                    imageStream[uri] = stream!!
                }
            }
        }
        ImagesContainer(imagesUri) {uri ->
            imagesUri.remove(uri)
            imageStream.remove(uri)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Title")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = titleText.value,
                onValueChange = { titleText.value = it },
                label = { Text(text = "Enter title") },
                modifier = Modifier.weight(1f),
                isError = titleText.value == ""
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Description")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = descriptionText.value,
                onValueChange = { descriptionText.value = it },
                label = { Text(text = "Enter description") },
                modifier = Modifier.weight(1f),
                isError = descriptionText.value == ""
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Brand")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = brandText.value,
                onValueChange = { brandText.value = it },
                label = { Text(text = "Enter a brand") },
                modifier = Modifier.weight(1f),
                isError = brandText.value == ""
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Condition")
            Spacer(modifier = Modifier.width(16.dp))
            DropDown(selectedCondition.value, { selectedCondition.value = it }, ProductDTO.Condition.values().map { it.name }, "Condition")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Visibility")
            Spacer(modifier = Modifier.width(16.dp))
            DropDown(selectedVisibility.value, { selectedVisibility.value = it }, ProductDTO.Visibility.values().map { it.name }, "Visibility")
        }
        Spacer(modifier = Modifier.height(16.dp))

        CategoriesRow(primaryCategories, categoryViewModel, sizeViewModel, selectedPrimaryCategory.value, { selectedPrimaryCategory.value = it },
            selectedSecondaryCategory.value, { selectedSecondaryCategory.value = it },
            selectedTertiaryCategory.value, { selectedTertiaryCategory.value = it },
            selectedMaterial.value, { selectedMaterial.value = it }, selectedColor.value, { selectedColor.value = it },
            selectedSize.value, { selectedSize.value = it }, selectedGender.value, { selectedGender.value = it },
            selectedLanguage.value, { selectedLanguage.value = it })

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Delivery size")
            Spacer(modifier = Modifier.width(16.dp))
            DropDown(selectedDeliverySize.value, { selectedDeliverySize.value = it }, ProductDTO.ProductSize.values().map { it.name }, "Delivery size")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Price")
            Spacer(modifier = Modifier.width(16.dp))
            CommonTextField(
                title = "Enter a price",
                placeholder = "Enter a price",
                text = priceText.value,
                onValueChange = { priceText.value = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Delivery price")
            Spacer(modifier = Modifier.width(16.dp))
            CommonTextField(
                title = "Enter a price",
                placeholder = "Enter a price",
                text = deliveryPriceText.value,
                onValueChange = { deliveryPriceText.value = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Currency")
            Spacer(modifier = Modifier.width(16.dp))

            DropDown(selectedCurrency.value, { selectedCurrency.value = it }, CustomMoneyDTO.Currency.values().map { it.name }, "Currency")
        }

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 16.dp)) {
            Button(onClick = {
                if(titleText.value == "" || descriptionText.value == "" || brandText.value == "" || priceText.value == "" || priceText.value.toDoubleOrNull() == null || priceText.value.toDouble() < 0.0
                    || deliveryPriceText.value == "" || deliveryPriceText.value.toDoubleOrNull() == null || deliveryPriceText.value.toDouble() < 0.0 || selectedCondition.value == ""
                    || selectedVisibility.value == "" || selectedDeliverySize.value == "" || selectedPrimaryCategory.value == "" || selectedSecondaryCategory.value == ""
                    || selectedTertiaryCategory.value == "" || selectedCurrency.value == "" || imagesUri.size == 0) {
                    mToast(context, "Please fill all the fields correctly")
                    return@Button
                }
                var category = ProductCategoryDTO(
                    null,
                    selectedPrimaryCategory.value,
                    selectedSecondaryCategory.value,
                    selectedTertiaryCategory.value
                )
                var productCost = CustomMoneyDTO(
                    priceText.value.toDouble(),
                    CustomMoneyDTO.Currency.valueOf(selectedCurrency.value)
                )
                var deliveryCost = CustomMoneyDTO(
                    deliveryPriceText.value.toDouble(),
                    CustomMoneyDTO.Currency.valueOf(selectedCurrency.value)
                )
                var product = ProductCreateDTO(
                    titleText.value,
                    descriptionText.value,
                    productCost,
                    deliveryCost,
                    brandText.value,
                    ProductCreateDTO.Condition.valueOf(selectedCondition.value),
                    ProductCreateDTO.ProductSize.valueOf(selectedDeliverySize.value),
                    ProductCreateDTO.Visibility.valueOf(selectedVisibility.value),
                    category,
                    null,
                    selectedPrimaryCategory.value,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )

                when (selectedPrimaryCategory.value) {
                    "Home" -> {
                        if(selectedColor.value == "" || selectedSize.value == "" || selectedMaterial.value == "") {
                            mToast(context, "Please fill all the fields")
                            return@Button
                        }
                        product.colour = HomeCreateDTO.Colour.valueOf(selectedColor.value)
                        product.homeSize = SizeDTO(null, selectedSize.value, "Home")
                        product.homeMaterial = HomeCreateDTO.HomeMaterial.valueOf(selectedMaterial.value)
                    }
                    "Entertainment" -> {
                        if(selectedLanguage.value == "") {
                            mToast(context, "Please fill all the fields")
                            return@Button
                        }
                        product.entertainmentLanguage = EntertainmentCreateDTO.EntertainmentLanguage.valueOf(selectedLanguage.value)
                    }
                    "Clothing" -> {
                        if(selectedColor.value == "" || selectedSize.value == "" || selectedGender.value == "") {
                            mToast(context, "Please fill all the fields")
                            return@Button
                        }
                        product.colour = HomeCreateDTO.Colour.valueOf(selectedColor.value)
                        product.clothingSize = SizeDTO(null, selectedSize.value, selectedSecondaryCategory.value)
                        product.productGender = ClothingCreateDTO.ProductGender.valueOf(selectedGender.value)
                    }
                }
                println("producti: " + product)
                newProductViewModel.saveNewProduct(product, context, imagesUri)
                mToast(context, "Product created")
            }) {
                Text("Load product")
            }
        }
    }
}

@Composable
fun ImagesContainer(imageUri: List<Uri?>, onDelete: (Uri) -> Unit) {
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
        items(imageUri) {uri ->
            val context = LocalContext.current
            val bitmap =  remember {
                mutableStateOf<Bitmap?>(null)
            }

            uri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { it1 -> BoxImage(it1, { onDelete(it) }) }
            }
        }
    }
}

@Composable
fun BoxImage(btm: Bitmap, onDeleteUri: () -> Unit) {
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
            IconButton(
                onClick = onDeleteUri,
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
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            isError = selectedOptionText == ""
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
            isError = selectedOptionText == ""
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
fun CategoriesRow(primaryCat: State<List<String>>, categoriesViewModel: ProductCategoryViewModel, sizesViewModel: SizeViewModel,
                  selectedPrimaryCategory: String, onPrimaryCatChange: (String) -> Unit, selectedSecondaryCategory: String, onSecondaryCatChange: (String) -> Unit,
                  selectedTertiaryCategory: String, onTertiaryCatChange: (String) -> Unit,
                  selectedMaterial: String, onMaterialChange: (String) -> Unit, selectedColor: String, onColorChange: (String) -> Unit,
                  selectedSize: String, onSizeChange: (String) -> Unit, selectedGender: String, onGenderChange: (String) -> Unit,
                  selectedLanguage: String, onLanguageChange: (String) -> Unit) {


    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Categories")
        DropDownCategory(selectedPrimaryCategory, { onPrimaryCatChange(it) }, list = primaryCat, "Category")
    }
    if(selectedPrimaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getSecondaryCategories(selectedPrimaryCategory)
        var secondaryCategories = categoriesViewModel.secondaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedSecondaryCategory, { onSecondaryCatChange(it) }, list = secondaryCategories, "Category")
        }
    }

    if(selectedSecondaryCategory != "") {
        Spacer(modifier = Modifier.height(16.dp))

        categoriesViewModel.getTertiaryCategories(selectedSecondaryCategory)
        var tertiaryCategories = categoriesViewModel.tertiaryCategories.collectAsState(initial = emptyList());
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
            Spacer(modifier = Modifier.weight(1f))
            DropDownCategory(selectedTertiaryCategory, { onTertiaryCatChange(it) }, list = tertiaryCategories, "Category")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    if(selectedTertiaryCategory != "") {
        when(selectedPrimaryCategory) {
            "Home" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Material", modifier = Modifier.weight(1f))
                    DropDown(selectedMaterial, { onMaterialChange(it) }, HomeCreateDTO.HomeMaterial.values().map { it.name }, "Material")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Color", modifier = Modifier.weight(1f))
                    DropDown(selectedColor, { onColorChange(it) }, HomeCreateDTO.Colour.values().map { it.name }, "Color")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    sizesViewModel.getSizesByCategory("Home")
                    Text("Size", modifier = Modifier.weight(1f))
                    DropDownCategory(selectedSize, { onSizeChange(it) }, sizesViewModel.sizesByCat.collectAsState(initial = emptyList()), "Size")
                }
            }
            "Entertainment" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Language", modifier = Modifier.weight(1f))
                    DropDown(selectedLanguage, { onLanguageChange(it) }, EntertainmentCreateDTO.EntertainmentLanguage.values().map { it.name }, "Language")
                }
            }
            "Clothing" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Gender", modifier = Modifier.weight(1f))
                    DropDown(selectedGender, { onGenderChange(it) }, ClothingCreateDTO.ProductGender.values().map { it.name }, "Gender")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Color", modifier = Modifier.weight(1f))
                    DropDown(selectedColor, { onColorChange(it) }, ClothingCreateDTO.Colour.values().map { it.name }, "Color")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    sizesViewModel.getSizesByCategory(selectedSecondaryCategory)
                    Text("Size", modifier = Modifier.weight(1f))
                    DropDownCategory(selectedSize, { onSizeChange(it) }, sizesViewModel.sizesByCat.collectAsState(emptyList()), "Size")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    title: String,
    placeholder: String,
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier.width(150.dp),
        label = {
            Text(text = title)
        },
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        isError = text == "" || text.toDoubleOrNull() == null || text.toDouble() <= 0.0
    )
}

private fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}