package com.example.presentation.ui.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.ui.theme.Blue
import com.example.presentation.ui.theme.Grey0
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.Grey4
import com.example.presentation.ui.theme.Red
import com.example.presentation.ui.theme.number
import com.example.presentation.ui.theme.tabText
import com.example.presentation.viewmodel.FavoriteViewModel

data class BottomNavItem(
    val title: String,
    val icon: Painter,
    val routeGraph: String,
    val routeScreen: String
)

@Composable
fun AppNavBar(
    favoritesVacanciesViewModel: FavoriteViewModel = hiltViewModel(),
    navViewModel: NavViewModel = viewModel(),
    navController: NavController,
) {
    val activity = LocalContext.current as? ComponentActivity
    val stateGraph by navViewModel.stateGraph.collectAsState()
    val allFavoritesVacancies by favoritesVacanciesViewModel.allFavoritesVacancies.collectAsState()

    val items = listOf(
        BottomNavItem(
            title = "Поиск",
            icon = painterResource(id = R.drawable.ic_nav_search),
            routeGraph = "home_graph",
            routeScreen = "home"
        ),
        BottomNavItem(
            title = "Избранное",
            icon = painterResource(id = R.drawable.ic_nav_favorite),
            routeGraph = "favorites_graph",
            routeScreen = "favorites"
        ),
        BottomNavItem(
            title = "Отклики",
            icon = painterResource(id = R.drawable.ic_nav_response),
            routeGraph = "responses_graph",
            routeScreen = "responses"
        ),
        BottomNavItem(
            title = "Сообщения",
            icon = painterResource(id = R.drawable.ic_nav_message),
            routeGraph = "messages_graph",
            routeScreen = "messages"
        ),
        BottomNavItem(
            title = "Профиль",
            icon = painterResource(id = R.drawable.ic_nav_profile),
            routeGraph = "profile_graph",
            routeScreen = "profile"
        )
    )

    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(50.dp),
        containerColor = Grey0,
        contentColor = Color.Blue
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            val currentRoute = navController.currentBackStackEntry?.destination?.route
                            if (stateGraph[index].isSelected && currentRoute != item.routeScreen) {
                                navController.navigate(item.routeGraph) {
                                    popUpTo(item.routeScreen)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            } else {
                                navViewModel.replaceOrAddGraph(item.routeGraph)
                                navViewModel.updateSelectedGraph(item.routeGraph)
                                navController.navigate(item.routeGraph) {
                                    popUpTo(navController.graph.id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Grey1)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(contentAlignment = Alignment.TopEnd) {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.title,
                                tint = if (stateGraph[index].isSelected) Blue else Grey4
                            )
                            if (index == 1 && allFavoritesVacancies.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(Red),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        color = Color.White,
                                        style = number,
                                        text = "${allFavoritesVacancies.size}"
                                    )
                                }
                            }
                        }
                        Text(
                            text = item.title,
                            style = tabText.copy(color = if (stateGraph[index].isSelected) Blue else Grey4)
                        )
                    }
                }
            }
        }
    }

    val routes = items.map { it.routeGraph }
    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != null && currentRoute !in routes && navViewModel.getLastGraph() != null) {
            navViewModel.updateSelectedGraph(navViewModel.getLastGraph()!!) // Обновили выбранный граф в BottomNavigation
            navViewModel.setSourceGraph(navViewModel.getLastGraph()!!) // Обновили название последнего графа в стеке
            navController.navigate(navViewModel.getLastGraph()!!) { // Перешли к нужному графу
                popUpTo(navController.graph.id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            navViewModel.deleteLastGraph() // Удалили последний граф из стека
        } else {
            activity?.finish()
        }
    }
}