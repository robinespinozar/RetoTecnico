package com.raerossi.retotecnico.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raerossi.retotecnico.ui.features.splash.SplashScreen

@Composable
fun RetoTecnicoApp() {
    val navController = rememberNavController()
    RootNavHost(navController)
}

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                toLoginScreen = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                }
            )
        }
        composable(route = Graph.MAIN) {
            MainNavHost()
        }
    }
}