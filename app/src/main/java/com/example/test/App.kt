package com.example.test

import android.app.Application
import com.example.test.api.API

class App : Application() {

    init {
        instance = this
    }

    var api: API? = null

    companion object {
        var instance: App? = null
    }
}