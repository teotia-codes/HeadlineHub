package com.example.newsapk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsapk.domain.usecases.AppEntryUseCases
import com.example.newsapk.presentation.navgraph.NavGraph
import com.example.newsapk.presentation.onboarding.OnBoardingScreen
import com.example.newsapk.presentation.onboarding.OnBoardingViewModel
import com.example.newsapk.ui.theme.NewsApkTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   val viewModel by viewModels<MainViewModel>()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            viewModel.splashCondition
        }

        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            NewsApkTheme {
                // A surface container using the 'background' color from the theme
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                      val startDestination = viewModel.startDestination
                        NavGraph(startDestination = startDestination)
                    }

                }
            }
        }
    }
}


