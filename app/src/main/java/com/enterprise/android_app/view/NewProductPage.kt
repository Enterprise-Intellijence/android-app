import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enterprise.android_app.R
import com.enterprise.android_app.ui.theme.AndroidappTheme

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductPage() {
    var titleText by mutableStateOf("")
    var descriptionText by mutableStateOf("")

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
        // TODO: aggiungere categorie, brand, condizioni e visibilità

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
            painter = painterResource(id = R.drawable._0200525_181714),
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