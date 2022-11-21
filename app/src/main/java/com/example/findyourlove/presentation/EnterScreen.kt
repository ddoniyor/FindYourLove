package com.example.findyourlove.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourlove.LoveScreen
import com.example.findyourlove.ui.theme.FindYourLoveTheme
import com.example.findyourlove.R

@Composable
fun EnterScreen(navController: NavController) {

    val context = LocalContext.current

    var selected by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgraund),
            contentDescription = "background",
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
                painter = painterResource(id = R.drawable.loveme),
                contentDescription = "loveme",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.padding(top = 85.dp))
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                RadioButton(
                    selected = selected,
                    onClick = { selected = !selected },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorResource(id = R.color.red_project),
                        unselectedColor = colorResource(id = R.color.red_project)
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.red_project),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = buildAnnotatedString {
                        append("I agree with the ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)){
                            append("terms of the user agreement")
                        }
                        append(" and the ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)){
                            append("privacy policy")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(top = 80.dp))
            Button(
                onClick = {
                    if (selected) {
                        navController.navigate(LoveScreen.Choose.screen_route)
                    } else {
                        Toast.makeText(context, "Accept privacy policy!", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(60),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.red_project),
                    contentColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Text(text = "PROCEED", fontSize = 18.sp)
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnterScreenPreview() {
    val context = LocalContext.current
    FindYourLoveTheme(darkTheme = true) {
        EnterScreen(navController = NavController(context))
    }
}
