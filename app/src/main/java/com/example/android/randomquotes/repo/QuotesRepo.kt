package com.example.android.randomquotes.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.randomquotes.api.QuoteService
import com.example.android.randomquotes.models.QuoteList

class QuotesRepo(private val quoteService: QuoteService) {

    private val quotesLiveData=MutableLiveData<QuoteList>()

    val quotes:LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result=quoteService.getQuoteList(page)
        if(result?.body()!=null){
            quotesLiveData.postValue(result.body())
        }
    }
}