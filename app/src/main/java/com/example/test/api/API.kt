package com.example.test.api

import com.example.test.model.CamerasListResponse
import com.example.test.model.ServerVersionResponse
import com.example.test.model.SnapshotsListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("product/version")
    suspend fun getServerVersion(): ServerVersionResponse

    @GET("live/media/snapshot/Server1/DeviceIpint.23/SourceEndpoint.video:0:0")
    suspend fun getSnapshotsList(): SnapshotsListResponse

    @GET("camera/list")
    suspend fun getCamerasList(): CamerasListResponse
}