package com.jhonny.punkbeer.presenter.navigation

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
    object DetailScreen: AppScreens("detail_screen")
}
