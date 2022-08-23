package com.example.android.randomquotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.randomquotes.models.Result

@Database(entities = [Result::class], version = 1)
abstract class QuoteDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object{
        @Volatile
        private var INSTANCE:QuoteDB?=null

        fun getDatabase(context: Context):QuoteDB{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,
                    QuoteDB::class.java,
                    "quoteDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}