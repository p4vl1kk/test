package com.example.test.model

data class Camera(
    val id: String,
    val name: String
)

data class CamerasListResponse(
    val cameras: List<Camera>
)