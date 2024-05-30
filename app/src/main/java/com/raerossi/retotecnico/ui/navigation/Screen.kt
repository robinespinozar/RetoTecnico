package com.raerossi.retotecnico.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object MoviesListScreen: Screen("movies_list_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}