package com.example.android.randomquotes

import android.app.Application
import com.example.android.randomquotes.api.QuoteService
import com.example.android.randomquotes.api.RetrofitHelper
import com.example.android.randomquotes.db.QuoteDB
import com.example.android.randomquotes.repo.QuotesRepo

class QuoteApplication:Application() {
    lateinit var quotesRepo:QuotesRepo
    override fun onCreate() {
        super.onCreate()
        initialise()
    }

    private fun initialise() {

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database=QuoteDB.getDatabase(applicationContext)
        quotesRepo=QuotesRepo(quoteService,database,applicationContext)
    }
}