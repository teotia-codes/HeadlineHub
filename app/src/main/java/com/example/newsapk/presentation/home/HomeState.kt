package com.example.newsapk.presentation.home


data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)