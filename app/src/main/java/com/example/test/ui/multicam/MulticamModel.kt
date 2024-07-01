package com.example.test.ui.multicam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class MulticamModel : ViewModel() {

    private val _state = MutableLiveData(MulticamState())
    val state: LiveData<MulticamState> = _state

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = App.instance?.api
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        loading = true,
                        error = false
                    )
                }
                val cameraList = api?.getCamerasList()
                val cameras = (cameraList?.cameras?: listOf())
                    .filter{ it.isActivated }

                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        cameras = cameras,
                        loading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Connection error: ${e.message}", e)
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(
                        cameras = listOf(),
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }
}
