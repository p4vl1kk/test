package com.example.test

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun createApi(baseUrl: String, username: String, password: String): API {
        val authInterceptor = Interceptor { chain ->
            val credentials = Credentials.basic(username, password)
            val request = chain.request().newBuilder()
                .addHeader("Authorization", credentials)
                .build()
            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(API::class.java)
    }
}
