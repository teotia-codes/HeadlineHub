package com.example.newsapk.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapk.presentation.onboarding.OnBoardingScreen
import com.example.newsapk.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
){
   val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route){
            composable(
                route= Route.NewsNavigatorScreen.route
            ){
                     Text(text = "News Navigator Screen")
            }
        }
    }

}