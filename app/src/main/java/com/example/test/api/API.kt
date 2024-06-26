package com.example.test.api

import com.example.test.model.ServerVersionResponse
import retrofit2.http.GET

interface API {
    @GET("product/version")
    suspend fun getServerVersion(): ServerVersionResponse

    // сюда будем добавлять другие методы апи
}


