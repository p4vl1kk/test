package com.example.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun getServerVersion(baseUrl: String, username: String, password: String, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            try {
                val api = RetrofitClient.createApi(baseUrl, username, password)
                val response = api.getServerVersion()
                if (response.isSuccessful) {
                    response.body()?.let { onResult(Result.success(it.version)) }
                } else {
                    onResult(Result.failure(Exception("Failed to get server version")))
                }
            } catch (e: Exception) {
                onResult(Result.failure(e))
            }
        }
    }
}
