package com.example.kotlintest.http

import android.util.Log
import com.example.kotlintest.Beens.Access_TokenBeens
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.BuildConfig
import java.util.concurrent.TimeUnit

class Api {
    lateinit var service: IRetrofit

    private object Holder {
        val INSTANCE = Api()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun init() {
        val builder = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.e("retrofit url",it)
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("url")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        service = retrofit.create(IRetrofit::class.java)
    }
}

