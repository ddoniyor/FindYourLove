package com.example.findyourlove

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class LoveScreen(val screen_route: String) {
    object Enter: LoveScreen("enter_screen")
    object Choose : LoveScreen("choose_screen")
    object Camera : LoveScreen("camera_screen")
    object Settings : LoveScreen("settings_screen")
    object Internet: LoveScreen("internet_connection")
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoveApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold() {
        NavGraph(navController = navController)
    }
}