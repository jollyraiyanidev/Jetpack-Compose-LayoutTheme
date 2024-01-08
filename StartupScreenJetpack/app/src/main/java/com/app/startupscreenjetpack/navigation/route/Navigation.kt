package com.app.startupscreenjetpack.navigation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.startupscreenjetpack.navigation.NavigationItem
import com.app.startupscreenjetpack.ui.screen.AutoSlide
import com.app.startupscreenjetpack.ui.screen.HomeScreen
import com.app.startupscreenjetpack.ui.screen.LoginScreen
import com.app.startupscreenjetpack.ui.theme.StartupScreenJetpackTheme
import com.app.startupscreenjetpack.ui.viewmodel.UserViewModel

@Composable
fun Navigation () {
    val navController = rememberNavController()
    StartupScreenJetpackTheme {
        NavHost(navController = navController, startDestination =NavigationItem.Splash.route) {

            composable(NavigationItem.Splash.route) {

                AutoSlide(navController =  navController)
            }
            composable(NavigationItem.Login.route) {
                LoginScreen(navController = navController)
            }
            composable(NavigationItem.Home.route) {
               // val viewModel = viewModel<UserViewModel>()
                val viewModel1: UserViewModel= hiltViewModel()

                HomeScreen(navController = navController,viewModel1)
            }

        }
    }
}

