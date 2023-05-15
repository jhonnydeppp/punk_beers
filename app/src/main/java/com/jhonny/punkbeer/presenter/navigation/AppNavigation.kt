package com.jhonny.punkbeer.presenter.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhonny.punkbeer.presenter.detail.DetailScreen
import com.jhonny.punkbeer.presenter.entities.BeerPresentation
import com.jhonny.punkbeer.presenter.main.MainScreen
import com.jhonny.punkbeer.presenter.main.MainViewModel
import com.jhonny.punkbeer.util.parcelable

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(navController, viewModel)
        }
        composable(route = AppScreens.DetailScreen.route /*+ "/{beer}", arguments =
        listOf(navArgument(name = "beer") {
            type = NavType.StringType

        })*/
        ) {
            val beer: Parcelable? =  it.arguments?.parcelable("beer")
            val result =
            navController.previousBackStackEntry?.savedStateHandle?.get<BeerPresentation?>("beer")
            DetailScreen(navController, result?: BeerPresentation())

        }
    }
}