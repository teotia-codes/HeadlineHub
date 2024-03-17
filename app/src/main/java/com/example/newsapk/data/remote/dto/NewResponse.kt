package com.example.newsapk.data.remote.dto

import com.example.newsapk.domain.model.Article

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)