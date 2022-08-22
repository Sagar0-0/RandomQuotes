package com.example.android.randomquotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.randomquotes.models.QuoteList
import com.example.android.randomquotes.repo.QuotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private var repo:QuotesRepo):ViewModel(){

    init{
        viewModelScope.launch(Dispatchers.IO) {
            repo.getQuotes(1);
        }
    }
    val quotes:LiveData<QuoteList>
    get()=repo.quotes
}