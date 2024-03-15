package com.example.newsapk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.newsapk.domain.usecases.AppEntryUseCases
import com.example.newsapk.presentation.onboarding.OnBoardingScreen
import com.example.newsapk.presentation.onboarding.OnBoardingViewModel
import com.example.newsapk.ui.theme.NewsApkTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var useCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            useCases.readAppEntry().collect{
                Log.d("Test",it.toString())
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            NewsApkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                        val viewModel: OnBoardingViewModel = hiltViewModel()
                        OnBoardingScreen(
                            event = viewModel::onEvent
                        )
                    }

                }
            }
        }
    }
}

