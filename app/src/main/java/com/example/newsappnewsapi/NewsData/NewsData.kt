package com.example.newsappnewsapi.NewsData

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,

    // ARTICLE
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,

    //
    val id: String,
    val name: String
)