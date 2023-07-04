package com.example.newsappnewsapi.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappnewsapi.NewsData.Article
import com.example.newsappnewsapi.NewsData.NewsResponse
import com.example.newsappnewsapi.R
import com.example.newsappnewsapi.UkNews.UkActivity
import com.example.newsappnewsapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
//import kotlinx.android.synthetic.main.activity_main.newsRecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://newsapi.org/"

class MainActivity : AppCompatActivity() {

    // view binding for the activity
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!



    lateinit var MyAdapter: MyAdapter
    lateinit var LinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // VIEWBINDING
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        getMyData()
        Refresh()

        LinearLayoutManager = LinearLayoutManager(this)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager
    }

    private fun getMyData() {
        val Retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NewsapiInterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<NewsResponse> = Retrofitbuilder.getBreakingNews()
                if (response.isSuccessful) {
                    val newsData = response.body()?.articles
                    withContext(Dispatchers.Main) {
                        MyAdapter = MyAdapter(baseContext, newsData ?: emptyList())
                        binding.newsRecyclerView.adapter = MyAdapter
                    }
                } else {
                    Log.e("MainActivity", "Error fetching data: ${response.code()}")
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext,"Error ${response.code()}, Refresh or restart the app", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching data: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext,"Error 404, Refresh or restart the app", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun Refresh() {
        val refreshBtn = findViewById<Button>(R.id.refreshBtn)
        refreshBtn.setOnClickListener {
            val Intent = Intent(this, UkActivity::class.java)
            startActivity(Intent)

            overridePendingTransition(0, 0)
            android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
        }

    }

}