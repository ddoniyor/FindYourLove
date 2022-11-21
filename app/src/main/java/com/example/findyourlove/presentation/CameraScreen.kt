package com.example.findyourlove.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.findyourlove.*
import com.example.findyourlove.R


@Composable
fun CameraScreen(navController: NavController) {
    var isClicked by remember { mutableStateOf(false)}
    Log.d("Koli","Camera screen recomposing")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.backgraund_wi_fi),
            contentDescription = "background_camera",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Image(painter = painterResource(id = R.drawable.ic_back_button),
            contentDescription = "back_button",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .align(Alignment.TopStart)
                .padding(10.dp)
                .clickable {
                    navController.navigateUp()
                })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(20.dp)
        ) {
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                backgroundColor = colorResource(id = R.color.red_card_view)
            ) {
                Box{
                    if (shouldShowPhoto.component1()) {
                        Image(
                            painter = rememberImagePainter(photoUri),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Button(
                    onClick = {
                        isClicked = !isClicked

                    },
                    shape = RoundedCornerShape(60),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(R.color.red_project),
                        contentColor = Color.White),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 25.dp, end = 25.dp, top = 115.dp, bottom = 115.dp)
                ) {
                    Text(text = "TURN ON CAMERA", fontSize = 18.sp)
                }
            }
        }
        if (isClicked){
            shouldShowCamera.value = true
            TakePhoto()

        }

    }
}

@Composable
fun TakePhoto(){
    if (shouldShowCamera.value) {
        Log.d("Kilo","shouldShowCamera.value ${shouldShowCamera.value}")
        CameraView(
            outputDirectory = outputDirectory,
            executor = cameraExecutor,
            onImageCaptured = ::handleImageCapture,
            onError = { Log.e("kilo", "View error:", it) }
        )
    }


}