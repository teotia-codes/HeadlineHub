package com.example.newsapk.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapk.domain.model.Article
import com.example.newsapk.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}