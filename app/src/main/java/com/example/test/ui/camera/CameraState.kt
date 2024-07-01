package com.example.test.ui.camera

import android.graphics.Bitmap
import com.example.test.model.camera_list.Camera

data class CameraState(
    val bitmap: Bitmap? = null,
    val loading: Boolean = false,
    val error: Boolean = false
)
