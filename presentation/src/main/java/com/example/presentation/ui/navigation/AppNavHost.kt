package com.example.presentation.ui.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.presentation.ui.screens.AuthorizationScreen
import com.example.presentation.ui.screens.ConfirmScreen
import com.example.presentation.ui.screens.FavoriteScreen
import com.example.presentation.ui.screens.HomeScreen
import com.example.presentation.ui.screens.MessageScreen
import com.example.presentation.ui.screens.ProfileScreen
import com.example.presentation.ui.screens.ResponseScreen
import com.example.presentation.ui.screens.SearchScreen
import com.example.presentation.ui.screens.SplashScreen
import com.example.presentation.ui.screens.VacancyScreen
import com.example.presentation.viewmodel.VacancyViewModel

@Composable
fun AppNavHost(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = "splash_screen",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("splash_screen") { SplashScreen(navController = navController, context = context) }

        navigation(startDestination = "auth_screen", route = "auth_graph") {
            composable("auth_screen") { AuthorizationScreen(navController = navController) }
            composable("confirm_screen") { ConfirmScreen(navController = navController, context = context) }
        }

        navigation(startDestination = "home", route = "home_graph") {
            composable("home") { HomeScreen(navController = navController) }
            composable("search") { SearchScreen(navController = navController) }
            composable(
                route = "vacancy/{vacancyId}",
                arguments = listOf(navArgument("vacancyId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vacancyViewModel: VacancyViewModel = hiltViewModel(backStackEntry)
                VacancyScreen(viewModel = vacancyViewModel, navController = navController)
            }
        }

        navigation(startDestination = "favorites", route = "favorites_graph") {
            composable("favorites") { FavoriteScreen(navController = navController) }
            composable(
                route = "vacancy/{vacancyId}",
                arguments = listOf(navArgument("vacancyId") { type = NavType.StringType })
            ) { backStackEntry ->
                val vacancyViewModel: VacancyViewModel = hiltViewModel(backStackEntry)
                VacancyScreen(viewModel = vacancyViewModel, navController = navController)
            }
        }

        navigation(startDestination = "responses", route = "responses_graph") {
            composable("responses") { ResponseScreen(navController = navController) }
        }

        navigation(startDestination = "messages", route = "messages_graph") {
            composable("messages") { MessageScreen(navController = navController) }
        }

        navigation(startDestination = "profile", route = "profile_graph") {
            composable("profile") { ProfileScreen(navController = navController) }
        }

    }
}