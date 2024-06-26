package com.example.test.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.ApiFactory
import com.example.test.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableLiveData(LoginState())
    val state: LiveData<LoginState> = _state


    fun connect(url: String, login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = ApiFactory.createApi(url, login, password)
                val ver = api.getServerVersion()
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = ver.version)
                }
                //save credencials to preferences
                App.instance?.api = api
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _state.value = state.value?.copy(version = e.message ?: "error")
                }
            }
        }
    }

    fun init() {
        // проверить наличие в настойках ранее сохранённые credencials
        ///todo connect([saved values])
    }
}
