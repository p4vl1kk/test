package com.example.test.ui.multicam

import com.example.test.model.camera_list.Camera

data class MulticamState(
    val cameras: List<Camera> = listOf(),
    val loading: Boolean = false,
    val error: Boolean = false
)
