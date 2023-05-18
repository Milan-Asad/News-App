package com.example.newsappnewsapi.MainActivity

import com.example.newsappnewsapi.NewsData.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsapiInterface {
    companion object {
        const val API_KEY = "520d8359e3e043dd85dc0c66af5b7f0d"
    }

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>


}
