package com.example.findyourlove.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findyourlove.R

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val checkedStateCamera = remember { mutableStateOf(true) }
    val checkedStateHd = remember { mutableStateOf(true) }
    val radioOptions = listOf("Look for men", "Look for women")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

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

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SETTINGS",
            fontSize = 30.sp,
            color = colorResource(id = R.color.red_project),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Camera Permission", modifier = Modifier.weight(2f), fontSize = 18.sp)
                Switch(
                    modifier = Modifier.weight(0.5f),
                    checked = checkedStateCamera.value,
                    onCheckedChange = { checkedStateCamera.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.red_project),
                        uncheckedThumbColor = colorResource(id = R.color.black)
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "HD Connection", modifier = Modifier.weight(2f), fontSize = 18.sp)
                Switch(
                    modifier = Modifier.weight(0.5f),
                    checked = checkedStateHd.value,
                    onCheckedChange = { checkedStateHd.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.red_project),
                        uncheckedThumbColor = colorResource(id = R.color.black)
                    )
                )
            }


            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.weight(2f),
                        fontSize = 18.sp
                    )
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        modifier = Modifier.weight(0.5f),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = colorResource(id = R.color.red_project),
                            unselectedColor = colorResource(id = R.color.black)
                        )
                    )

                }
            }
        }

    }

}