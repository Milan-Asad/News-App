package com.example.newsappnewsapi

import com.example.newsappnewsapi.NewsData.NewsData

data class NewsResponse(
    val articles: List<NewsData>
)
