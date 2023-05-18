package com.example.newsappnewsapi.UkNews

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappnewsapi.NewsData.NewsData
import com.example.newsappnewsapi.databinding.NewsCardviewBinding

class UkNewsAdapter(val context: Context, val NewsData: List<NewsData>) :
    RecyclerView.Adapter<UkNewsAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: NewsCardviewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            var headline: TextView = binding.newsHeadline
            var date: TextView = binding.newsDate
            var author: TextView = binding.newsAuthor
            var image: ImageView = binding.newsImage
            var source: TextView = binding.newsSource


            fun bind(newsData: NewsData) {
                headline.text = newsData.title
                date.text = newsData.publishedAt
                author.text = newsData.author
                source.text = newsData.source.name

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = NewsCardviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val newsData = NewsData[position]
            holder.bind(newsData)
        }

        override fun getItemCount(): Int {
            return NewsData.size
        }
    }
