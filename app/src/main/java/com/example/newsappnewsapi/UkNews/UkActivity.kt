package com.example.newsappnewsapi.UkNews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappnewsapi.MainActivity.BASE_URL
import com.example.newsappnewsapi.MainActivity.MainActivity
import com.example.newsappnewsapi.MainActivity.MyAdapter
import com.example.newsappnewsapi.MainActivity.NewsapiInterface
import com.example.newsappnewsapi.MainActivity.UkNewsApiInterface
import com.example.newsappnewsapi.NewsData.NewsResponse
import com.example.newsappnewsapi.R
import com.example.newsappnewsapi.databinding.ActivityMainBinding
import com.example.newsappnewsapi.databinding.ActivityUkNewsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://newsapi.org/"

class UkActivity : AppCompatActivity() {

    private var _binding : ActivityUkNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var myAdapter: UkNewsAdapter // Declare MyAdapter as a property

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uk_news)
        // VIEWBINDING
        _binding = ActivityUkNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var linearLayoutManager: LinearLayoutManager

        linearLayoutManager = LinearLayoutManager(this)
        binding.UkNewsRecyclerView.layoutManager = linearLayoutManager

        getUkNewsData()
        Refresh()


    }

    private fun getUkNewsData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(UkNewsApiInterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<NewsResponse> = retrofitBuilder.getUKBreakingNews()
                if (response.isSuccessful) {
                    val newsData = response.body()?.articles
                    withContext(Dispatchers.Main) {
                        myAdapter = UkNewsAdapter(baseContext, newsData ?: emptyList())
                        binding.UkNewsRecyclerView.adapter = myAdapter // Assign the adapter instance
                    }
                } else {
                    Log.e("UkActivity", "Error fetching data: ${response.code()}")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext,"Error ${response.code()}, Refresh or restart the app", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("UkActivity", "Error fetching data: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext,"Error 404, Refresh or restart the app", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun Refresh() {
        val refreshBtn = findViewById<Button>(R.id.testbtn)
        refreshBtn.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

    }


}