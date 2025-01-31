package com.example.presentation.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.presentation.utils.getConfirmationStatus

@Composable
fun SplashScreen(
    navController: NavHostController,
    context: Context
) {
    LaunchedEffect(Unit) {
        val isConfirmed = getConfirmationStatus(context)
        if (isConfirmed) {
            navController.navigate("home_graph") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            navController.navigate("auth_graph") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { }
}