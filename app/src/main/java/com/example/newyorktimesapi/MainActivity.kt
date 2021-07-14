package com.example.newyorktimesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.newsappwithapi.NewsApi
import com.example.newsappwithapi.dataWeb.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var newsData: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsData = findViewById(R.id.textView)
        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NewsApi::class.java)
        val call = service.getNews()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() == 200) {
                    val newsResponse = response.body()!!

                    val stringBuilder = "Section is : " + newsResponse.section +
                            "\n" + newsResponse.results[0].title +
                            "\n" + newsResponse.results[1].title +
                            "\n" + newsResponse.results[2].title +
                            "\n" + newsResponse.results[3].title +
                            "\n" + newsResponse.results[4].title + "bjhkh ddgg"


                    newsData!!.text = stringBuilder

                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                newsData!!.text = t.message
            }
        })
    }



}