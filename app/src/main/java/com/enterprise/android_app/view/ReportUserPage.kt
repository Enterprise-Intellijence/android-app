package com.enterprise.android_app.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.enterprise.android_app.R
import com.enterprise.android_app.model.CurrentDataUtils
import com.enterprise.android_app.navigation.Navigation
import com.enterprise.android_app.view_models.ReportedViewModel
import com.enterprise.android_app.view_models.UserViewModel
import io.swagger.client.models.ReportDTO
import io.swagger.client.models.UserBasicDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportUserPage(navController: NavHostController, reportedUserId: String) {
    val reportedViewModel: ReportedViewModel = viewModel()
    var text by remember { mutableStateOf(TextFieldValue()) }


    if (reportedViewModel.reportedUser == null || reportedViewModel.reportedUser!!.id != reportedUserId) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp)
            )
            reportedViewModel.loadReportedUser(reportedUserId)
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                Text(
                    buildAnnotatedString {
                        append("You are reporting user: ")

                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                            append(reportedViewModel.reportedUser!!.username)
                        }

                        append(". Write a meaningful description of the problem.")
                    }
                )

            }


            Spacer(modifier = Modifier.height(16.dp))

            Row{
                TextField(
                    value = text,
                    onValueChange = {
                        if (it.text.length <= 200) {
                            text = it
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    textStyle = TextStyle(fontSize = 18.sp),
                    singleLine = false,
                    maxLines = 10,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            ImeAction.Done
                        }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    placeholder = { Text( text = stringResource(R.string.report_placeholder)) },
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = text.text.length.toString() + "/200",
                    fontSize = 12.sp,
                    style = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontStyle = FontStyle.Italic)
                )

                Button(
                    onClick = {
                        val report = ReportDTO(
                            description = text.text,
                            reportedUser = reportedViewModel.reportedUser!!,
                            reporterUser = CurrentDataUtils.currentUser?.let { CurrentDataUtils.toUserBasicDTO( it) }
                        )

                        reportedViewModel.report(report)
                        navController.popBackStack()
                        mToast(navController.context, "Report sent. Thanks for your feedback!")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(16.dp)
                ) {
                    Text(text = stringResource(R.string.report))
                }
            }
        }
    }
}

fun mToast(context: Context, text: String){
    var toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.BOTTOM, 0, 0)
    toast.show()
}