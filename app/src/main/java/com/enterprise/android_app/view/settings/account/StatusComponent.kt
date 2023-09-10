package com.enterprise.android_app.view.settings.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.ui.theme.TransparentGreenButton
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.models.UserDTO


@Composable
fun StatusComponent(navController: NavController){
    val userViewModel = UserViewModel()
    var user: MutableState<UserDTO?> = remember { mutableStateOf(CurrentDataUtils.currentUser) }

    val onHoliday: MutableState<String> = rememberSaveable { mutableStateOf("ACTIVE") }

    val modifier = Modifier.fillMaxWidth()

    val isPopupVisible = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val circleColor = getCircleColor(onHoliday.value)

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    ).clickable {
                        isPopupVisible.value = true
                    }
            )
            Text("Status")
            Spacer(modifier = Modifier.weight(1f)) // Spazio vuoto per allineare il bottone a destra
            TransparentGreenButton(
                onClick = {
                    if(onHoliday.value == "HOLIDAY")
                        onHoliday.value = "ACTIVE"
                    else
                        onHoliday.value = "HOLIDAY"
                    user.value?.let { userViewModel.changeStatus(it.id, onHoliday.value) }
                },
                buttonName = "change"
            )
        }
    }


    if (isPopupVisible.value) {
        AlertDialog(
            onDismissRequest = { isPopupVisible.value = false },
            title = { Text("Legenda colori") },
            text = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(
                                    color = Color.Green,
                                    shape = CircleShape
                                )
                        )
                        Text("Utente attivo")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(
                                    color = Color.Yellow,
                                    shape = CircleShape
                                )
                        )
                        Text("Utente in vacanza")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { isPopupVisible.value = false }
                ) {
                    Text("Chiudi")
                }
            }
        )
    }

    }


@Composable
private fun getCircleColor(status: String): Color {
    return if (status == "HOLIDAY") Color.Yellow else Color.Green
}