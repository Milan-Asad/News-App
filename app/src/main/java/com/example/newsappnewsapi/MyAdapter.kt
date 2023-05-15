package com.example.newsappnewsapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappnewsapi.NewsData.NewsData
import com.example.newsappnewsapi.databinding.NewsCardviewBinding


class MyAdapter(val context: Context, val NewsData: List<NewsData>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NewsCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val headline: TextView = binding.newsHeadline
        val description: TextView = binding.newsDescription

        fun bind(newsData: NewsData) {
            headline.text = newsData.title
            description.text = newsData.publishedAt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
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