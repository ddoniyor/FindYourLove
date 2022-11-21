package com.example.findyourlove

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourlove.presentation.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LoveScreen.Enter.screen_route
    ) {
        composable(route = LoveScreen.Enter.screen_route) {
            EnterScreen(navController)
        }
        composable(
            route =  LoveScreen.Choose.screen_route
        ) {
            ChooseScreen(navController)
        }
        composable(
            route =  LoveScreen.Camera.screen_route
        ) {
            CameraScreen(navController)
        }
        composable(
            route =  LoveScreen.Settings.screen_route
        ) {
            SettingsScreen(navController)
        }
        composable(
            route =  LoveScreen.Internet.screen_route
        ) {
            CheckInternetConnection(navController)
        }
    }
}