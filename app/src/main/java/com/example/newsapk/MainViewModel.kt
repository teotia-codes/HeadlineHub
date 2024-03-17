package com.example.newsapk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapk.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapk.presentation.navgraph.Route
import javax.inject.Inject
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@HiltViewModel
class MainViewModel  @Inject
    constructor(
        private val appEntryUseCases: AppEntryUseCases
    )
    : ViewModel() {
        var splashCondition by mutableStateOf(true)
            private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
init {
    appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen->
        if (shouldStartFromHomeScreen){
            startDestination = Route.NewsNavigation.route
        }else {
            startDestination = Route.AppStartNavigation.route
        }
        delay(300)
        splashCondition = false
    }.launchIn(viewModelScope)
}

}