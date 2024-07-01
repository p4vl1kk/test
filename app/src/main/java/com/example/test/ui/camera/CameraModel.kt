package com.example.test.ui.camera

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import com.example.test.model.camera_list.Camera

class CameraModel : ViewModel() {

    private val _state = MutableLiveData(CameraState())
    val state: LiveData<CameraState> = _state

    fun init(camera: Camera){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = App.instance?.api
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        loading = true,
                        error = false
                    )
                }
                val cameraId = camera.videoStreams
                    .first()
                    .accessPoint
                    .replaceFirst("hosts/", "")
                val responseBody = api?.getSnapshot(cameraId)
                val bytes = responseBody?.bytes() ?: byteArrayOf()
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        bitmap = bitmap,
                        loading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Connection error: ${e.message}", e)
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        bitmap = null,
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }
}
