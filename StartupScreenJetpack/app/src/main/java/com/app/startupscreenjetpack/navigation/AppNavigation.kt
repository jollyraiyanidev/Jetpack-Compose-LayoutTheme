package com.app.startupscreenjetpack.navigation

enum class Screen {
    HOME,
    LOGIN,
    Splash,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Splash : NavigationItem(Screen.Splash.name)
}