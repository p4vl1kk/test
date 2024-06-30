package com.example.test.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.ApiFactory
import com.example.test.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData(LoginState())
    val state: LiveData<LoginState> = _state

    fun connect(url: String, login: String, password: String, onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = ApiFactory.createApi(url, login, password)
                val ver = api.getServerVersion()
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = ver.version, error = "")
                    onSuccess(true) // Вызываем колбэк успешной авторизации
                }
                App.instance?.api = api
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Connection error: ${e.message}", e)
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = "", error = e.message ?: "Unknown error")
                    onSuccess(false) // Вызываем колбэк неудачной авторизации
                }
            }
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = ApiFactory.createApi("http://try.axxonsoft.com:8000/asip-api/", "root", "root")
                val ver = api.getServerVersion()
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = ver.version, error = "")
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Initialization error: ${e.message}", e)
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = "", error = e.message ?: "Unknown error")
                }
            }
        }
    }
}
