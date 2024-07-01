package com.example.test.api

import com.example.test.model.ServerVersionResponse
import com.example.test.model.camera_list.CameraList
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("product/version")
    suspend fun getServerVersion(): ServerVersionResponse

    @GET("live/media/snapshot/{videoSourceId}")
    suspend fun getSnapshot(
        @Path(value = "videoSourceId", encoded = true) camId: String,
    ): ResponseBody

    @GET("camera/list")
    suspend fun getCamerasList(): CameraList
}