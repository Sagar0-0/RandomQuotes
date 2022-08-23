package com.example.android.randomquotes.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.randomquotes.api.QuoteService
import com.example.android.randomquotes.db.QuoteDB
import com.example.android.randomquotes.models.QuoteList
import com.example.android.randomquotes.util.NetworkUtils

class QuotesRepo(
    private val quoteService: QuoteService,
    private val quoteDB: QuoteDB,
    private val applicationContext: Context
) {

    private val quotesLiveData=MutableLiveData<QuoteList>()

    val quotes:LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result=quoteService.getQuoteList(page)
            if(result?.body()!=null){
                quoteDB.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }else{
            val quotes=quoteDB.quoteDao().getQuotes()
            val quoteList=QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }

    }
}