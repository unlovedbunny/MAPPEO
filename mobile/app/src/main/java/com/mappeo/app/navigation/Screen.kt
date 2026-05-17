package com.mappeo.app.navigation

/**
 * Define todas as rotas de navegação do aplicativo Mappeo.
 * Utiliza sealed class para garantir type-safety em toda a navegação.
 */
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Map : Screen("map")
    object Profile : Screen("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
}
