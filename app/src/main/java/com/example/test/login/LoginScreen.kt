package com.example.test.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    var url by remember { mutableStateOf("http://try.axxonsoft.com:8000/asip-api/") }
    var login by remember { mutableStateOf("root") }
    var password by remember { mutableStateOf("root") }
    val context = LocalContext.current

    val state = viewModel.state.observeAsState(initial = LoginState()).value

    DisposableEffect(Unit) {
        viewModel.init()

        onDispose {
            // если бы нам было нужно что-то "выключить" в модели после ухода с этого экрана,
            // то это делалось бы отсюда
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Server URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.connect(url, login, password)
        }) {
            Text("Login")
        }

        // примерно так отобразится картинка на плитке
        //Image(bitmap = state.bitmap.asImageBitmap(), contentDescription = null)
    }

    LaunchedEffect(state.version) {
        if (state.version.isNotEmpty())
            Toast.makeText(context, "Server Version: ${state.version}", Toast.LENGTH_SHORT).show()
        // успешный коннект! отсюда открываем следующий экран
    }

    LaunchedEffect(state.error) {
        if (state.error.isNotEmpty())
            Toast.makeText(context, "Error: ${state.error}", Toast.LENGTH_SHORT).show()
    }

}
