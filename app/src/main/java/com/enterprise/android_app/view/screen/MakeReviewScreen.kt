package com.enterprise.android_app.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enterprise.android_app.R
import com.enterprise.android_app.view.components.ButtonComponent
import com.enterprise.android_app.view.components.NormalTextComponent
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import io.swagger.client.apis.ReviewControllerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview()
fun MakeReviewScreen(){
    val reviewController: ReviewControllerApi = ReviewControllerApi()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            var rating by remember {
                mutableStateOf(5f)
            }

            val emoji by derivedStateOf {
                when (rating){
                    1f -> "\uD83D\uDE2D"
                    2f -> "\uD83D\uDE22"
                    3f -> "\uD83D\uDE1F"
                    4f -> "\uD83D\uDE42"
                    else -> "\uD83D\uDE01"
                }
            }

            NormalTextComponent(value = stringResource(id = R.string.reviewing))//aggiungere nome persona
            
            //HeadingTextComponent(value = stringResource(id = R.string.review_insert))
            Spacer(modifier = Modifier.height(16.dp))
            /*
            TextFieldComponent(

                labelValue = stringResource(id = R.string.review_insert),
                painterResource = painterResource(id = R.drawable.filled_visibility),
            )
            */

            Text(text = stringResource(R.string.give_a_vote),
                textAlign = TextAlign.Start
            )
            Row(horizontalArrangement =Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth() ) {
                Column() {
                    RatingBar(
                        value = rating,
                        style = RatingBarStyle.Default,
                        onValueChange = {rating = it},
                        onRatingChanged = {},
                    )
                }
                Column() {
                    Text(text = emoji, fontSize = 30.sp)

                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            ButtonComponent(
                value = stringResource(id = R.string.submit_review),
                onClickAction = {
                    val reviewController = ReviewControllerApi()
                    CoroutineScope(Dispatchers.IO).launch {
                        //reviewController.createReview()
                    }

                }
            )




        }

    }
}







