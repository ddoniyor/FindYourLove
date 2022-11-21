package com.example.findyourlove.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourlove.R


@Composable
fun CheckInternetConnection(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgraund_wi_fi),
            contentDescription = "background_wi_fi",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.wi_fi_connection),
                contentDescription = "wi_fi_connection",
                modifier = Modifier
                    .fillMaxWidth()
                    .width(200.dp)
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.padding(top = 15.dp))
            Text(
                text = "CHECK YOUR INTERNET CONNECTION",
                fontSize = 30.sp,
                color = colorResource(id = R.color.red_project),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(top = 45.dp))
            Button(
                onClick = {
                    navController.navigateUp()
                },
                shape = RoundedCornerShape(60),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.red_project),
                    contentColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(350.dp)
                    .height(70.dp)
            ) {
                Text(text = "CANCEL", fontSize = 18.sp)
            }
        }
    }
}