package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test.login.LoginScreen
import com.example.test.login.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = loginViewModel,
                navigateToDestination = { navController.navigate("destination") }
            )
        }
        composable("destination") {
            MainScreen(
                navigateToSnapshots = { navController.navigate("snapshots") },
                navigateToCameras = { navController.navigate("cameras") }
            )
        }
        composable("snapshots") {
            SnapshotsScreen()
        }
        composable("cameras") {
            CamerasScreen()
        }
    }
}
