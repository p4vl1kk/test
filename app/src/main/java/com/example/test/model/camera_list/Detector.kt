package com.example.test.model.camera_list

data class Detector(
    val accessPoint: String,
    val displayName: String,
    val events: List<String>,
    val isActivated: Boolean,
    val parentDetector: String,
    val type: String
)