package com.example.android.randomquotes.api

import com.example.android.randomquotes.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    fun getQuoteList(@Query("page")pageNo:Int):Response<QuoteList>
}