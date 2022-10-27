package com.example.android.randomquotes

import android.app.Application
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.randomquotes.api.QuoteService
import com.example.android.randomquotes.api.RetrofitHelper
import com.example.android.randomquotes.repo.QuotesRepo
import com.example.android.randomquotes.viewmodel.MainViewModel
import com.example.android.randomquotes.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = (application as QuoteApplication).quotesRepo
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]

        mainViewModel.quotes.observe(this,Observer{
            Toast.makeText(this, it.results.toString(), Toast.LENGTH_SHORT).show();
        })
    }
}