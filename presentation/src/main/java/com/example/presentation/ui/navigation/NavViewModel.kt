package com.example.presentation.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class GraphItem(
    val routeGraph: String,
    val isSelected: Boolean
)

@HiltViewModel
class NavViewModel @Inject constructor() : ViewModel() {

    private val _navStack = MutableStateFlow<List<String>>(emptyList())
    val navStack: StateFlow<List<String>> = _navStack

    private val _stateGraph = MutableStateFlow<List<GraphItem>>(
        listOf(
            GraphItem("home_graph", true),
            GraphItem("favorites_graph", false),
            GraphItem("responses_graph", false),
            GraphItem("messages_graph", false),
            GraphItem("profile_graph", false)
        )
    )
    val stateGraph: StateFlow<List<GraphItem>> = _stateGraph

    private val _sourceGraph = MutableStateFlow<String>("home_graph")
    val sourceGraph: StateFlow<String> = _sourceGraph

    private val _likedVacanciesCount = MutableStateFlow(0)
    val likedVacanciesCount: StateFlow<Int> = _likedVacanciesCount

    fun updateSelectedGraph(routeGraph: String) {
        _stateGraph.value = _stateGraph.value.map {
            it.copy(isSelected = it.routeGraph == routeGraph)
        }
    }

    fun replaceOrAddGraph(routeGraph: String) {
        if (_sourceGraph.value != routeGraph) {
            val currentStack = _navStack.value.toMutableList()

            if (currentStack.contains(_sourceGraph.value)) {
                currentStack.remove(_sourceGraph.value)
            }

            currentStack.remove(routeGraph)
            currentStack.add(_sourceGraph.value)

            _navStack.value = currentStack
            println(_navStack.value)
            _sourceGraph.value = routeGraph
        }
    }

    fun deleteLastGraph() {
        val currentStack = _navStack.value.toMutableList()

        if (currentStack.isNotEmpty()) {
            currentStack.removeAt(currentStack.lastIndex)
        }

        _navStack.value = currentStack
    }

    fun setSourceGraph(routeGraph: String) {
        _sourceGraph.value = routeGraph
    }

    fun getLastGraph(): String? = _navStack.value.lastOrNull()
}