package com.example.test

import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("version")
    suspend fun getServerVersion(): Response<ServerVersionResponse>
}

data class ServerVersionResponse(val version: String)
