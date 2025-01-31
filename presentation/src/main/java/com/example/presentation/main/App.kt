package com.example.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.navigation.AppNavBar
import com.example.presentation.ui.navigation.AppNavHost
import com.example.presentation.ui.theme.Grey0
import com.example.presentation.ui.theme.HeadHunterTheme

@Composable
fun App() {
    HeadHunterTheme {
        val navController = rememberNavController()
        val context = LocalContext.current
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val noBottomBarRoutes = listOf(
            "splash_screen",
            "auth_screen",
            "confirm_screen"
        )
        Scaffold(
            containerColor = Grey0,
            bottomBar = {
                AnimatedVisibility(
                    visible = currentRoute !in noBottomBarRoutes,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    AppNavBar(navController = navController)
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                AppNavHost(navController = navController, context = context)
            }
        }
    }
}