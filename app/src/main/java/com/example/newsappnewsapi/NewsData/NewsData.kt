package com.example.newsappnewsapi.NewsData

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,

    // MINE
    val author: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String
)